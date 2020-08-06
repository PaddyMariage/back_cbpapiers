package cbpapiers.app.cbpapiers.controller;

import cbpapiers.app.cbpapiers.dao.*;
import cbpapiers.app.cbpapiers.model.*;
import cbpapiers.app.cbpapiers.model.pk.DiscountPK;
import cbpapiers.app.cbpapiers.model.pk.OrderLinePK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AlimBDDController {

    private ArticleDAO articleDAO;
    private CustomerDAO customerDAO;
    private CityDAO cityDAO;
    private DiscountDAO discountDAO;
    private OrderDAO orderDAO;

    @Autowired
    public AlimBDDController(ArticleDAO articleDAO, CustomerDAO customerDAO,
                             CityDAO cityDAO, DiscountDAO discountDAO, OrderDAO orderDAO) {
        this.articleDAO = articleDAO;
        this.customerDAO = customerDAO;
        this.cityDAO = cityDAO;
        this.discountDAO = discountDAO;
        this.orderDAO = orderDAO;
    }


    /**
     * En premier remplissons la table City
     */
    @PostMapping("/villes")
    public boolean addVille(@RequestBody Customer[] customers) {

        // A partir des customers nous devons créer une liste de City uniques.
        // On instancie une liste de City.
        List<City> cities = new ArrayList<>();

        for (Customer customer : customers) {

            // Réglage du nom de la ville, toujours en majuscule
            // histoire d'être tranquille avec les Metz vs METZ vs MEtz par exemple.
            // On .trim() pour enlever les espaces (en début et fin) ou \n (retour à la ligne).
            String ville = customer.getCT_Ville().trim();

            // Réglage du code postal, s'il contient F- je l'enlève, sinon je copie direct,
            // on .trim() pour enlever les espaces ou \n (retour à la ligne).
            String cp;
            if (customer.getCT_CodePostal().contains("F-"))
                cp = customer.getCT_CodePostal().replace("F-", "").trim();
            else
                cp = customer.getCT_CodePostal().trim();

            // Si le customer n'est pas endormi (inactif) on va enregistrer la ville.
            if (customer.getCT_Sommeil() == 0) {

                // Si les 2 champs ne sont pas vides on crée et on ajoute une City à la liste.
                if (!ville.equals("") && !cp.equals(""))
                    cities.add(new City(ville.toUpperCase().trim(), cp.trim()));
            }
        }


        // On trie la liste (compareTo est défini dans la classe City, on trie par le nom de ville).
        cities.sort(City::compareTo);

        // On enlève les doublons
        List<City> noDupeCities = cities.stream().distinct().collect(Collectors.toList());

        // pour chaque city de noDupeCities on enregistre la city dans la BDD.
        noDupeCities.forEach(city -> cityDAO.save(city));
        return true;

        // Dans la réalité j'ai quand même eu des doublons :
        // - fautes de frappes
        // - des noms de villes composés parfois avec et sans les tirets
        // - parfois avec et sans accents
        // - même nom, CP différent
        // Du coup, j'suis allé manuellement dans la BDD enlever les doublons de noms (uniquement doublon de nom)
        // on associera alors la ville à une utilisateur via le nom de la ville (qui sera unique du coup),
        // quitte à ce que le CP soit incorrect.
        // c'est pour facile pour enregistrer les customers, on n'aura pas à faire pour tous les cas de figure
        // et on ne risque pas d'avoir des cas qui sortiront 2 résultats
        // au pire, certains utilisateurs n'auront pas de ville (dans le JSON y'a des champs qui manquent anyway)

    }


    /**
     * En deuxième remplissons la table Customer
     */
    @PostMapping("/clients")
    public boolean addClients(@RequestBody Customer[] customers) {

        for (Customer customer : customers) {

            // Si le customer n'est pas endormi (inactif)
            if (customer.getCT_Sommeil() == 0) {
                // Je set ce que je peux set directement par correspondance
                // entre le json (les transients) et nos propriétés (champs bdd).
                // Je .trim() partout car je suis devenu parano avec leur BDD.
                customer.setId(customer.getCT_Num().trim());
                customer.setName(customer.getCT_Intitule().trim());
                customer.setAddress(customer.getCT_Adresse().toUpperCase().trim());
                customer.setCountry(customer.getCT_Pays().toUpperCase().trim());
                customer.setPhoneNumber(customer.getCT_Telephone().trim());
                customer.setEmail(customer.getCT_EMail().trim());
                customer.setActive(true);
                customer.setAdmin(false);

                // Maintenant pour trouver la ville, il me faut aller chercher la ville
                // en fonction du nom de la ville (voir méthode au-dessus pour le pourquoi).

                // Réglage du nom de la ville, toujours en majuscule
                // histoire d'être tranquille avec les Metz vs METZ vs MEtz par exemple.
                // On .trim() pour enlever les espaces ou \n (retour à la ligne)
                String ville = customer.getCT_Ville().toUpperCase().trim();
                City city = cityDAO.findByName(ville).orElse(null);

                // On associe cette ville trouvée (ou null) à notre customer pour
                // la clé étrangère dans la BDD (ou l'absence de clé étrangère)
                customer.setCity(city);

                // Il ne reste plus qu'à enregistrer notre customer.
                customerDAO.save(customer);

            }
        }

        return true;

        // Cas intéressant, "MAIZIERE LES METZ" et "MAIZIERE LÈS METZ",
        // il n'arrive pas à faire la différence, il sort donc 2 résultats au lieu d'un
        // lors de la recherche de la ville. Du coup ça buggait.
        // J'ai donc supprimé MAIZIERE LÈS METZ de la BDD et corrigé dans le json
        // le seul customer avec l'accent sur le LÈS
    }

    /**
     * En troisième, LES ARTICLES (le plus facile) !!
     * <p>
     * PS: Ca a pris 45min 29.76s pour tout enregistrer XD.
     * Il y avait quand même 45 695 articles à enregistrer.
     */

    @PostMapping("/articles")
    public boolean addArticles(@RequestBody Article[] articles) {

        // Je set ce que je peux set directement par correspondance
        // entre le json (les transients) et nos propriétés (champs bdd).
        // Je .toUpperCase() et .trim() partout car je suis devenu parano avec leur BDD.
        for (Article article : articles) {
            article.setReference(article.getAR_Ref().toUpperCase().trim());
            article.setLabel(article.getAR_Design().toUpperCase().trim());
            article.setFamily(article.getFA_CodeFamille().toUpperCase().trim());

            // Pour le prix faut parse en double, on récupère le prix en string, on remplace la virgule par un ..
            article.setUnitPrice(
                    Double.parseDouble(
                            article.getAR_PrixVen().replace(",", ".")
                    )
            );

            // J'enregistre les articles
            articleDAO.save(article);
        }
        return true;
    }


    /**
     * En pénultième, ARTCLIENT, joie !
     * J'écris la méthode en attendant que ça enregistre les 15 millions d'articles,
     * ça prend un peu de temps oui.
     * Edit: j'ai dû retouché à la méthode car:
     * - ce que je recevais était null (il fallait régler les jsonview pour la classe Discount)
     * - comme je n'ai pas enregistré les clients inactifs, ça tapait dans du vide dans la BDD à certains CT_Num
     * <p>
     * PS: ça n'a pris que 4min 18.36sec pour tout enregistrer : (
     * (que 12 513 entrées)
     */

    @PostMapping("/artclients")
    public boolean addArtClients(@RequestBody Discount[] discounts) {

        // Pour chaque ARTCLIENT (Discount) je récupère AR_Ref et CT_Num pour la clé composite
        for (Discount discount : discounts) {
            String AR_Ref = discount.getAR_Ref().toUpperCase().trim();
            String CT_Num = discount.getCT_Num().trim();

            // Je recherche si CT_Num me retourne un customer de ma BDD
            Customer customer = customerDAO.findById(CT_Num).orElse(null);

            // Si j'ai un retour autre que null, je crée et enregistre l'ARTCLIENT (Discount)
            if (customer != null) {

                // Je crée et associe la clé composite
                DiscountPK cle = new DiscountPK();
                cle.setIdCustomer(CT_Num);
                cle.setIdArticle(AR_Ref);
                discount.setDiscountPK(cle);

                // Pareil que précédemment je fait ce qu'il faut pour chopper les prix et remises
                discount.setDiscount(Double.parseDouble(discount.getAC_Remise().replace(",", ".")));
                discount.setClientPrice(Double.parseDouble(discount.getAC_PrixVen().replace(",", ".")));

                // Je mets dans la BDD
                discountDAO.save(discount);
            }
        }
        return true;
    }

    /**
     * en dernier, les doclignes, CHOUETTE ! en plus ils vont utiliser notre idGenerator !
     * Donc tous les DO_PIECE auront le format MOBIX (X étant un nombre).
     * L'enregistrement des articles n'ayant pas fini, j'écris ça pendant que ça continue xd.
     * C'est accessoirement l'étape la plus difficile, en vrai.
     * <p>
     * Les pièges sont multiples :
     * - AR_Ref = "" <-> DL_Qte (quantité) = 0
     * <p>
     * - DL_Qte = un double. Je déconne pas, y'a des 0,5, même des 0,1 qui traînent
     * Au choix je pouvais mettre notre variable quantity en tant que double ou
     * si je reçois un double je le mets à 1. J'ai choisi de mettre à 1.
     * <p>
     * - CT_Num = ""
     * <p>
     * - 2 orderLines sont identiques sauf la qté.
     * Oui, y'a le même DO_Piece, date, CT_Num, AR_Ref, juste la qté change.
     * Ca crée des problèmes pour l'enregistrement à cause de notre clé composite "limitée" à 2 items.
     * <p>
     * - Ma première méthode (voir le bousin qui est commenté au-dessous de addOrders)
     * utilisait DO_Piece comme id de commande (pour me simplifier la vie),
     * mais elle demandait aussi à chaque orderLine de vérifier si la commande associée existe déjà.
     * Si oui, mettre à jour. Sauf que ça avait créé des soucis d'objets dans la session à un moment donné
     * (les objets sont les mêmes avec 2 hashcode différents apparemment, et spring aime pas ça
     * quand on manipule des bases de données).
     * error = a different object with the same identifier value was already associated with the session
     * <p>
     * Fun facts: il y a 95 191 doclignes pour 11 628 commandes.
     * (92 052 doclignes pour 11 059 commandes de clients actifs)
     * fini de tout ajouté en 1h 19 min 14.10s (à 01:30)
     */

    @PostMapping("/orders")
    public boolean addOrders(@RequestBody OrderLine[] orderLines) {

        // 1) je fais une liste de DO_Piece
        List<String> doPieces = new ArrayList<>();
        for (OrderLine orderLine : orderLines) {
            doPieces.add(orderLine.getDO_Piece().toUpperCase().trim());
        }

        // 2) J'enlève les doublons
        List<String> uniqueDoPieces = doPieces.stream().distinct().collect(Collectors.toList());

        // 3) pour chaque DO_Piece je vais créer un Set<OrderLine>
        // (ainsi j'aurais tous les orderLine qui ont le même DO_Piece)
        uniqueDoPieces.forEach(
                doPiece -> {
                    Set<OrderLine> orderLines_sameDoPiece = new HashSet<>();
                    Order order = new Order();

                    // je reparcours les orderLines envoyés en JSON afin d'alimenter mon Set<OrderLine>
                    for (OrderLine orderLine : orderLines) {

                        if (orderLine.getDO_Piece().equals(doPiece)) {
                            String AR_Ref = orderLine.getAR_Ref().toUpperCase().trim();
                            String qty = orderLine.getDL_Qte();

                            // je vérifie que AR_Ref et qty ne sont pas vides
                            if (!AR_Ref.equals("") && !qty.equals("")) {

                                // si la quantité est un nombre à virgule, j'le fous à 1
                                if (qty.contains(",")) {
                                    qty = "1";
                                }

                                // je set DL_Qte car il me servira à parse la quantité plus tard
                                // même si la valeur reste la même (quand le nombre n'est pas à virgule)
                                // je set, au cas où.
                                orderLine.setDL_Qte(qty);

                                // et j'ajoute l'orderLine dans mon Set<OrderLine>.
                                orderLines_sameDoPiece.add(orderLine);
                            }
                        }
                    }

                    // je crée un autre Set<OrderLine> pour enlever les duplicata,
                    // la méthode equals est déclarée dans la classe Order.
                    // en gros, pour éviter le problème de doublons sauf qté, je compare
                    // CT_Num, AR_Ref, DO_Piece et si ces 3 propriétés sont les mêmes entre 2 orderLines
                    // je laisse JAVA décider duquel supprimer, je m'en fous duquel reste au final
                    // (j'en avais un peu marre à minuit d'essayer d'avoir toute la data le plus fidèlement possible).
                    Set<OrderLine> orderLines_sameDoPiece_NoDupe = new HashSet<>(orderLines_sameDoPiece);

                    // je récupère un orderLine de ma liste sans doublon afin de récupérer le custommer en commun
                    OrderLine oneOrderLine = orderLines_sameDoPiece_NoDupe.stream().findFirst().orElse(null);

                    if (oneOrderLine != null) {
                        Customer customer = customerDAO.findById(oneOrderLine.getCT_Num().toUpperCase().trim()).orElse(null);

                        if (customer != null) {

                            // je crée ma commande que j'enregistre dans la BDD et je saveAndFlush
                            // afin de pouvoir récupérer l'orderNumber ! (MOBIX)
                            order.setCustomer(customer);

                            // pour la date, j'ai regardé sur le net
                            // https://www.javatpoint.com/java-string-to-date
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            Date date = null;
                            try {
                                date = formatter.parse(oneOrderLine.getDO_Date());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            order.setDateCommande(date);
                            orderDAO.saveAndFlush(order);

                            // Ahem, j'écris ces commentaires pendant que ça enregistre les commandes (il est 00:47)
                            // je devrais ici être en train de parcourir le Set orderLines_sameDoPiece_NoDupe...
                            // mais apparemment ça fonctionne aussi avec la liste qui a des dupes...
                            // pourquoi ? AUCUNE IDEE !
                            // Avant de faire le NoDupe ça ne fonctionnait pas.
                            orderLines_sameDoPiece.forEach(
                                    orderLine -> {

                                        // bref, je parcours les orderLine pour en créer la clé composite.
                                        // et régler la quantité
                                        OrderLinePK cle = new OrderLinePK();
                                        cle.setIdArticle(orderLine.getAR_Ref().toUpperCase().trim());
                                        cle.setIdOrder(order.getOrderNumber());
                                        orderLine.setOrderLinePK(cle);
                                        orderLine.setQuantity(Integer.parseInt(orderLine.getDL_Qte()));

                                    }
                            );

                            // on associe cette liste de doclignes à la commande.
                            order.setOrderLines(orderLines_sameDoPiece);

                            // on enregistre la commande.
                            orderDAO.save(order);
                        }
                    }
                }
        );


        return true;
    }

    @PostMapping("/test")
    public boolean checkPrix(@RequestBody Doclignes[] docs) {
        int counterOfSamePrice = 0;
        int counterOfArRef = 0;
        double prix = 0d;
        System.out.println(docs.length);
        for (Doclignes doc : docs) {
            Customer customer = customerDAO.findById(doc.getCT_Num().toUpperCase().trim()).orElse(null);
            if (customer != null) {
                for (Discount discount : customer.getDiscount()) {
                    discount.getArticle().setFinalPrice();
                }
            }
            if (doc.getAR_Ref().equals("")) {
                counterOfArRef++;
            } else {
                counterOfSamePrice++;
            }
        }

        System.out.println("nombre de AR_Ref vides : " + counterOfArRef +
                "\nnombre de AR_Ref non vides : " + counterOfSamePrice +
                "\nnombre de doclignes : " + docs.length +
                "\ntotal vide+nonvide : " + (counterOfArRef+counterOfSamePrice));

//        for (Doclignes doc : docs) {
//            for (Article article : articles) {
//                if (doc.getAR_Ref().equals(""))
//                   break;
//                else if (doc.getAR_Ref().toUpperCase().trim().equals(article.getReference())) {
//                    counterOfArRef++;
//                    prix = Double.parseDouble(doc.getDL_PrixUnitaire().replace(",", "."));
//                    if (prix == article.getUnitPrice()) {
//                        counterOfSamePrice++;
//                    }
//                    break;
//                }
//            }
//        }

//        System.out.println("résultat final:\nnombre d'articles : " + articles.size() +
//                "\nnombre de match sur AR_Ref : " + counterOfArRef +
//                "\nnombre de match sur le prix : " + counterOfSamePrice);
        return true;
    }

//     for (OrderLine orderLine : orderLines) {
//            Customer customer = customerDAO.findById(orderLine.getCT_Num().toUpperCase().trim()).orElse(null);
//            if (customer != null) {
//                Order order = orderDAO.findById(orderLine.getDO_Piece().toUpperCase().trim()).orElse(null);
//                if (order == null) {
//
//                    order = new Order();
//                    order.setCustomer(customer);
//                    order.setOrderNumber(orderLine.getDO_Piece().toUpperCase().trim());
//                    orderDAO.saveAndFlush(order);
//
//                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//                    Date date = formatter.parse(orderLine.getDO_Date());
//                    order.setDateCommande(date);
//
//                    OrderLinePK cle = new OrderLinePK();
//                    cle.setIdOrder(orderLine.getDO_Piece().toUpperCase().trim());
//                    cle.setIdArticle(orderLine.getAR_Ref().toUpperCase().trim());
//                    orderLine.setOrderLinePK(cle);
//                    orderLine.setOrder(order);
//                    orderLine.setQuantity(Integer.parseInt(orderLine.getDL_Qte()));
//
//                    Set<OrderLine> orderLinesForOrder = new HashSet<>();
//                    orderLinesForOrder.add(orderLine);
//                    order.setOrderLines(orderLinesForOrder);
//
//                    System.out.println("temps mort");
//                    orderDAO.save(order);
//
//                } else {
//                    OrderLinePK cle = new OrderLinePK();
//                    cle.setIdOrder(orderLine.getDO_Piece().toUpperCase().trim());
//                    cle.setIdArticle(orderLine.getAR_Ref().toUpperCase().trim());
//                    orderLine.setOrderLinePK(cle);
//                    orderLine.setQuantity(Integer.parseInt(orderLine.getDL_Qte()));
//                    order.getOrderLines().add(orderLine);
//
//                }
//
//            }
//        }
//        return true;
//    }

}

