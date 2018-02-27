package com.fernandocejas.android10.sample.presentation.view.drawer;

import com.fernandocejas.android10.sample.presentation.R;
import java.util.Arrays;
import java.util.List;

public class CategoryDataFactory {

  public static List<Category> makeGenres() {
    return Arrays.asList(makeRockGenre(),
        makeJazzGenre(),
        makeClassicGenre(),
        makeSalsaGenre(),
        makeBluegrassGenre());
  }

  public static Category makeRockGenre() {
    return new Category("Rock", makeRockArtists());
  }

  public static List<Feed> makeRockArtists() {
    Feed queen = new Feed("Queen", true, R.drawable.ic_home_black_24dp);
    Feed styx = new Feed("Styx", false, R.drawable.ic_home_black_24dp);
    Feed reoSpeedwagon = new Feed("REO Speedwagon", false, R.drawable.ic_home_black_24dp);
    Feed boston = new Feed("Boston", true, R.drawable.ic_home_black_24dp);

    return Arrays.asList(queen, styx, reoSpeedwagon, boston);
  }

  public static Category makeJazzGenre() {
    return new Category("Jazz", makeJazzArtists());
  }

  public static List<Feed> makeJazzArtists() {
    Feed milesDavis = new Feed("Miles Davis", true, R.drawable.ic_home_black_24dp);
    Feed ellaFitzgerald = new Feed("Ella Fitzgerald", true, R.drawable.ic_home_black_24dp);
    Feed billieHoliday = new Feed("Billie Holiday", false, R.drawable.ic_home_black_24dp);

    return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
  }

  public static Category makeClassicGenre() {
    return new Category("Classic", makeClassicArtists());
  }

  public static List<Feed> makeClassicArtists() {
    Feed beethoven = new Feed("Ludwig van Beethoven", false, R.drawable.ic_home_black_24dp);
    Feed bach = new Feed("Johann Sebastian Bach", true, R.drawable.ic_home_black_24dp);
    Feed brahms = new Feed("Johannes Brahms", false, R.drawable.ic_home_black_24dp);
    Feed puccini = new Feed("Giacomo Puccini", false, R.drawable.ic_home_black_24dp);

    return Arrays.asList(beethoven, bach, brahms, puccini);
  }

  public static Category makeSalsaGenre() {
    return new Category("Salsa", makeSalsaArtists());
  }

  public static List<Feed> makeSalsaArtists() {
    Feed hectorLavoe = new Feed("Hector Lavoe", true, R.drawable.ic_home_black_24dp);
    Feed celiaCruz = new Feed("Celia Cruz", false, R.drawable.ic_home_black_24dp);
    Feed willieColon = new Feed("Willie Colon", false, R.drawable.ic_home_black_24dp);
    Feed marcAnthony = new Feed("Marc Anthony", false, R.drawable.ic_home_black_24dp);

    return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
  }

  public static Category makeBluegrassGenre() {
    return new Category("Bluegrass", makeBluegrassArtists());
  }

  public static List<Feed> makeBluegrassArtists() {
    Feed billMonroe = new Feed("Bill Monroe", false, R.drawable.ic_home_black_24dp);
    Feed earlScruggs = new Feed("Earl Scruggs", false, R.drawable.ic_home_black_24dp);
    Feed osborneBrothers = new Feed("Osborne Brothers", true, R.drawable.ic_home_black_24dp);
    Feed johnHartford = new Feed("John Hartford", false, R.drawable.ic_home_black_24dp);

    return Arrays.asList(billMonroe, earlScruggs, osborneBrothers, johnHartford);
  }

}

