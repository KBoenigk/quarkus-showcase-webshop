package de.openknowledge.projects.webshop.infrastructure.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.filiale.FilialAdresse;
import de.openknowledge.projects.webshop.domain.bestellung.filiale.FilialName;
import de.openknowledge.projects.webshop.domain.bestellung.filiale.Filiale;
import de.openknowledge.projects.webshop.domain.zahlung.ZahlungsArt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class FilialRepository {
    private final List<Filiale> filialen = new ArrayList<>();

    @PostConstruct
    public void init() {
        FilialAdresse filialAdresseEssen = FilialAdresse.Builder()
                                                .setFilialAdressName("Pottsalat GmbH")
                                                .setStraßeNummer("Bismarckstraße 5")
                                                .setPostleitzahl("45128")
                                                .setOrt("Essen")
                                                .build();
        FilialName filialNameEssen = new FilialName("Pottsalat Essen");
        Filiale filialeEssen = Filiale.Builder()
                                .setName(filialNameEssen)
                                .setFilialAdresse(filialAdresseEssen)
                                .build();
        filialen.add(filialeEssen);

        FilialAdresse filialAdresseDortmund = FilialAdresse.Builder()
                                                .setFilialAdressName("Pottsalat GmbH")
                                                .setStraßeNummer("Rheinlanddamm 8")
                                                .setPostleitzahl("44139")
                                                .setOrt("Dortmund")
                                                .build();
        FilialName filialNameDortmund = new FilialName("Pottsalat Dortmund");
        Filiale filialeDortmund = Filiale.Builder()
                                .setName(filialNameDortmund)
                                .setFilialAdresse(filialAdresseDortmund)
                                .build();
        filialen.add(filialeDortmund);
    }

    public List<Filiale> read() {
        return Collections.unmodifiableList(this.filialen);
    }
}
