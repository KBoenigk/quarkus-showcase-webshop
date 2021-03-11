package de.openknowledge.projects.webshop.application.bestellung;

import org.apache.commons.lang3.Validate;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Ein DTO, das eine Bestellung repräsentiert
 */
public class BestellungDTO {

    @NotNull
    private List<ProduktAuswahlDTO> produktAuswahl;

    @NotNull
    private LieferAdresseDTO lieferAdresse;

    protected BestellungDTO() {
        super();
    }

    public BestellungDTO(final List<ProduktAuswahlDTO> produktAuswahl, final LieferAdresseDTO lieferAdresse) {
        this.produktAuswahl = Validate.notNull(produktAuswahl, "Produktauswahl darf nicht null sein.");
        this.lieferAdresse = Validate.notNull(lieferAdresse, "Lieferadresse darf nicht null sein.");
    }

    public List<ProduktAuswahlDTO> getProduktAuswahl() {
        return produktAuswahl;
    }

    public void setProduktAuswahl(List<ProduktAuswahlDTO> produktAuswahl) {
        this.produktAuswahl = produktAuswahl;
    }

    public LieferAdresseDTO getLieferAdresse() {
        return lieferAdresse;
    }

    public void setLieferAdresse(LieferAdresseDTO lieferAdresse) {
        this.lieferAdresse = lieferAdresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BestellungDTO that = (BestellungDTO) o;
        return produktAuswahl.equals(that.produktAuswahl) && lieferAdresse.equals(that.lieferAdresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produktAuswahl, lieferAdresse);
    }

    @Override
    public String toString() {
        return "BestellungDTO{" +
                "produktAuswahl=" + produktAuswahl +
                ", lieferAdresse=" + lieferAdresse +
                '}';
    }
}
