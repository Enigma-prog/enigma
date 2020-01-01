/*
 * Jan Kampherbeek, (c) 2019.
 * Enigma is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.be.persistency;

import com.radixpro.enigma.be.astron.assist.Location;
import com.radixpro.enigma.be.astron.assist.SimpleDate;
import com.radixpro.enigma.be.astron.assist.SimpleDateTime;
import com.radixpro.enigma.be.astron.assist.SimpleTime;
import com.radixpro.enigma.be.astron.core.SeFrontend;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class Database {
   private static final String FILEPATH = "./db/enigma.db";

   public Database() {
    ObjectRepository<PersistableConfiguration> repo;

    Nitrite enigmadb = Nitrite.builder().compressed().filePath(FILEPATH).openOrCreate();
    repo = enigmadb.getRepository("key", PersistableConfiguration.class);

    PersistableConfiguration config = new PersistableConfiguration();
    config.setAspectBaseOrb(8.0);
    config.setAspectOrbStruc(0);
    config.setAspectOrbType(1);
    config.setAyanamsha(0);
    config.setCelObjects(new int[]{6, 7, 8, 9});
    config.setEclProjection(0);
    config.setHouseSystem(4);
    config.setId(1);
    config.setObserverPos(0);
    config.setParentId(0);
    repo.insert(config);

    ObjectRepository<PersistableChartData> repoCharts;

    repoCharts = enigmadb.getRepository("key", PersistableChartData.class);
    PersistableChartData chartData = new PersistableChartData();
    chartData.setCategories(new int[]{2, 3, 4});
    chartData.setDescription("Description for dummy");
    chartData.setId(1);
    chartData.setLocation(new Location(52.0, 6.9));
    chartData.setName("Jan");
    chartData.setRating("AA");
    SimpleDate date = new SimpleDate(1953, 1, 29, true);
    SimpleTime time = new SimpleTime(7, 37, 30);
    chartData.setSimpleDateTime(new SimpleDateTime(SeFrontend.getFrontend(), date, time));
    repoCharts.insert(chartData);

    PersistableConfiguration config2 = repo.find(eq("id", 1)).firstOrDefault();
    System.out.println(config2.getAspectBaseOrb());
    PersistableChartData chart2 = repoCharts.find(eq("id", 1)).firstOrDefault();
    System.out.println(chart2.getName());
   }


}
