# language: nl
Functionaliteit: Doe berekening met +

  Scenario: Tel 2 nummers op
    Gegeven een hoofdactiviteit
    Als de gebruiker 123 invoert in het eerste tekstveld
    En de gebruiker 456 invoert in het tweede tekstveld
    En de gebruiker klikt op de plus knop
    Dan is het antwoord 579

  @skipAndroid
  Scenario: we gonna skip this one for android
    Given whatever setup
    When whatever event
    Then whatever assert