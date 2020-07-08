package cbpapiers.app.cbpapiers;

import java.util.NoSuchElementException;

public class NotFoundException extends NoSuchElementException {


    private final String relatedId;
    private final Class<?> type;

    public <T> NotFoundException(String relatedId, Class<T> type) {
        this.relatedId = relatedId;
        this.type = type;

    }

    public Class<?> getType() {
        return type;
    }

    public String getRelatedId() {
        return relatedId;
    }

    @Override
    public String getMessage(){
        return type.getSimpleName() + " not found with id : " + relatedId;
    }
}
