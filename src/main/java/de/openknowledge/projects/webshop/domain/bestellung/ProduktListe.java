package de.openknowledge.projects.webshop.domain.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.filiale.Filiale;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProduktListe {

    @NotNull
    private List<ProduktListenElement> produktListe;

    public ProduktListe(Builder b) {
        this.produktListe = b.produktListe;
    }

    public List<ProduktListenElement> getProduktListe() {
        return produktListe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktListe that = (ProduktListe) o;
        return produktListe.equals(that.produktListe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produktListe);
    }

    @Override
    public String toString() {
        return "ProduktListe{" +
                "produktListe=" + produktListe +
                '}';
    }

    public static Builder Builder() { return new Builder(); }

    public static class Builder {
        private List<ProduktListenElement> produktListe;

        private Builder() {
            this.produktListe = new ArrayList<>();
        }

        public Builder addProdukt(@NotNull ProduktListenElement produktListenElement) {
            this.produktListe.add(produktListenElement);

            return this;
        }

        /**
         * baut eine Instanz von ProduktListe, wenn mindestens ein {@link ProduktListenElement} hinzugefügt wurde
         *
         * @return ProduktListe
         * @throws ValidationException, wenn nicht alle benötigten Felder übergeben wurden
         */
        public ProduktListe build() {
            if (this.produktListe.isEmpty()) {
                throw new ValidationException("Filiale.Builder: Name darf nicht null sein!");
            }

            return new ProduktListe(this);
        }
    }
}
