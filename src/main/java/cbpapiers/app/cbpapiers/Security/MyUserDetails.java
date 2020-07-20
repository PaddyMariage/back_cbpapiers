package cbpapiers.app.cbpapiers.Security;

import cbpapiers.app.cbpapiers.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(Customer customer) {
        this.userName = customer.getId();
        this.password = customer.getPassword();
        this.active = customer.isActive();

        this.authorities=new ArrayList<>();
        if(customer.isAdmin()){
            this.authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            this.authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    //c'est ici qu'on récupère les rôles, on les récupère comme on veut mais il faut que dans getAuthorities il récupère les rôles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    //Username=champ unique : ca peut être n'importe quoi : identifiant, code, code de l'emprinte digitale, email, numéro de compte bancaire
    @Override
    public String getUsername() {
        return userName;
    }

    //session expirée
    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    //verrouille le compte
    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    //est-ce que son compte a été supprimé ?
    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    //est-ce qu'il est actif ou pas ?
    @Override
    public boolean isEnabled() {
        return active;
    }
}

