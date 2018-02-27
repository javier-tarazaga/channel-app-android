package com.fernandocejas.android10.sample.presentation.view.drawer;

import com.fernandocejas.android10.sample.presentation.R;
import java.util.Arrays;
import java.util.List;

public class GenreDataFactory {

  public static List<Genre> makeGenres() {
    return Arrays.asList(makeRockGenre(),
        makeJazzGenre(),
        makeClassicGenre(),
        makeSalsaGenre(),
        makeBluegrassGenre());
  }

  public static Genre makeRockGenre() {
    return new Genre("Rock", makeRockArtists());
  }

  public static List<Artist> makeRockArtists() {
    Artist queen = new Artist("Queen", true, R.drawable.ic_home_black_24dp);
    Artist styx = new Artist("Styx", false, R.drawable.ic_home_black_24dp);
    Artist reoSpeedwagon = new Artist("REO Speedwagon", false, R.drawable.ic_home_black_24dp);
    Artist boston = new Artist("Boston", true, R.drawable.ic_home_black_24dp);

    return Arrays.asList(queen, styx, reoSpeedwagon, boston);
  }

  public static Genre makeJazzGenre() {
    return new Genre("Jazz", makeJazzArtists());
  }

  public static List<Artist> makeJazzArtists() {
    Artist milesDavis = new Artist("Miles Davis", true, R.drawable.ic_home_black_24dp);
    Artist ellaFitzgerald = new Artist("Ella Fitzgerald", true, R.drawable.ic_home_black_24dp);
    Artist billieHoliday = new Artist("Billie Holiday", false, R.drawable.ic_home_black_24dp);

    return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
  }

  public static Genre makeClassicGenre() {
    return new Genre("Classic", makeClassicArtists());
  }

  public static List<Artist> makeClassicArtists() {
    Artist beethoven = new Artist("Ludwig van Beethoven", false, R.drawable.ic_home_black_24dp);
    Artist bach = new Artist("Johann Sebastian Bach", true, R.drawable.ic_home_black_24dp);
    Artist brahms = new Artist("Johannes Brahms", false, R.drawable.ic_home_black_24dp);
    Artist puccini = new Artist("Giacomo Puccini", false, R.drawable.ic_home_black_24dp);

    return Arrays.asList(beethoven, bach, brahms, puccini);
  }

  public static Genre makeSalsaGenre() {
    return new Genre("Salsa", makeSalsaArtists());
  }

  public static List<Artist> makeSalsaArtists() {
    Artist hectorLavoe = new Artist("Hector Lavoe", true, R.drawable.ic_home_black_24dp);
    Artist celiaCruz = new Artist("Celia Cruz", false, R.drawable.ic_home_black_24dp);
    Artist willieColon = new Artist("Willie Colon", false, R.drawable.ic_home_black_24dp);
    Artist marcAnthony = new Artist("Marc Anthony", false, R.drawable.ic_home_black_24dp);

    return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
  }

  public static Genre makeBluegrassGenre() {
    return new Genre("Bluegrass", makeBluegrassArtists());
  }

  public static List<Artist> makeBluegrassArtists() {
    Artist billMonroe = new Artist("Bill Monroe", false, R.drawable.ic_home_black_24dp);
    Artist earlScruggs = new Artist("Earl Scruggs", false, R.drawable.ic_home_black_24dp);
    Artist osborneBrothers = new Artist("Osborne Brothers", true, R.drawable.ic_home_black_24dp);
    Artist johnHartford = new Artist("John Hartford", false, R.drawable.ic_home_black_24dp);

    return Arrays.asList(billMonroe, earlScruggs, osborneBrothers, johnHartford);
  }

}

