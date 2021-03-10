package de.openknowledge.projects.webshop.infrastructure.bestellung;

import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProduktRepository implements PanacheRepository<Produkt> {

    public List<Produkt> read() {
        return this.listAll();
    }

    public Optional<Produkt> findByProduktName(final String name) {
        return find("name", name).firstResultOptional();
    }
//    private final List<Produkt> produkte = new ArrayList<>();
//
//    @PostConstruct
//    public void init() {
//        Produkt salat = Produkt.Builder()
//                        .setProduktName("Falafel Schmaus")
//                        .setProduktPreis(BigDecimal.valueOf(9.8))
//                        .setProduktBeschreibung("Ein leckerer Salat.").build();
//        produkte.add(salat);
//        Produkt bowl = Produkt.Builder()
//                .setProduktName("Rosinenbomber Bowl")
//                .setProduktPreis(BigDecimal.valueOf(11.4))
//                .setProduktBeschreibung("Eine leckere Bowl.").build();
//        produkte.add(bowl);
//        Produkt sticks = Produkt.Builder()
//                .setProduktName("Ciabatta Sticks")
//                .setProduktPreis(BigDecimal.valueOf(1)).build();
//        produkte.add(sticks);
//        Produkt bananenbrot = Produkt.Builder()
//                .setProduktName("Bananenbrot")
//                .setProduktPreis(BigDecimal.valueOf(2.4)).build();
//        produkte.add(bananenbrot);
//        Produkt getränk = Produkt.Builder()
//                .setProduktName("Lemonaid Maracuja")
//                .setProduktPreis(BigDecimal.valueOf(2.5))
//                .setProduktBeschreibung("Eine erfrischende Limo.").build();
//        produkte.add(bananenbrot);
//    }
//
//    public List<Produkt> read() {
//        return Collections.unmodifiableList(this.produkte);
//    }
}
