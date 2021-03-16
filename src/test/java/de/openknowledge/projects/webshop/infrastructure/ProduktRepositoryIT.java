package de.openknowledge.projects.webshop.infrastructure;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import de.openknowledge.projects.webshop.DatabaseTestResource;
import de.openknowledge.projects.webshop.domain.bestellung.Produkt;
import de.openknowledge.projects.webshop.infrastructure.bestellung.ProduktRepository;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@QuarkusTest
@QuarkusTestResource(DatabaseTestResource.class)
@DBRider
@DataSet(value = "webshop.yml", strategy = SeedStrategy.CLEAN_INSERT, skipCleaningFor = "flyway_schema_history")
public class ProduktRepositoryTest {

    @Inject
    private ProduktRepository repository;

    @Test
    public void readShouldReturnTwoProdukte() {
        List<Produkt> produktListe = repository.read();
        Assertions.assertThat(produktListe.size()).isEqualTo(2);
    }

    @Test
    public void nameOfSecondProduktShouldBeBowl() {
        List<Produkt> produktListe = repository.read();
        Assertions.assertThat(produktListe.get(1).getName()).isEqualTo("Bowl");
    }

    @Test
    public void preisOfFirstProduktShouldBeCorrect() {
        List<Produkt> produktListe = repository.read();
        Assertions.assertThat(produktListe.get(0).getPreis()).isEqualTo(BigDecimal.valueOf(8.9));
    }

    @Test
    public void beschreibungOfSecondProduktShouldBeCorrect() {
        List<Produkt> produktListe = repository.read();
        Assertions.assertThat(produktListe.get(0).getBeschreibung()).isEqualTo("Ein Salat");
    }
}
