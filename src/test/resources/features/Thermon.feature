@regression
Feature: Thermon - Find a Representative Page

  Background: Pre conditions
    Given I navigate "https://thermon.com" homepage
    Then Accept cookies

  @regression
  Scenario: Verify "Electrode Steam Boiler" product specification
    And open Products Catalog
    And Find the product "Electrode Steam Boiler" in the catalog in the "VAPOR POWER" category
    And click on "SPECIFICATIONS" tab
    Then verify product characteristics should contain:
      | Steam from: 800KW – 50,000KW (2,700 pph – 167,000 pph)    |
      | Operating pressure from 100 – 405 psi                     |
      | Voltages from: 4.16kV – 15kV                              |
      | Steam from: 800KW – 50,000KW (1,225 kg/hr – 75,771 kg/hr) |
      | Operating pressure from: 6.9 – 27.9 bar                   |
      | Voltages from: 4.16 kV – 15 kV                            |
      | Brewery & Distillation                                    |
      | Corrugated & Paper Processing                             |
      | Healthcare                                                |
      | Process Steam and Heat                                    |
      | Power Plant                                               |

  @regression
  Scenario: Verify "Jet Type Electrode Boiler" product specification
    And open Products Catalog
    And Find the product "Jet Type Electrode Boiler" in the catalog in the "PRECISION BOILERS" category
    And click on "SPECIFICATIONS" tab
    Then verify product characteristics should contain:
      | Steam from: 800KW – 50,000KW          |
      | Capacities: (2,700 pph – 167,000 pph) |
      | Operating pressure from 100 – 405 psi |
      | Voltages from: 4.16kV – 15kV          |

  @smoke
  Scenario Outline: Validate representatives offices for product and zip
    And I click Find a Representative link
    And fill Find A Representative Form with values: "<product>" "<country>" "<state>" "<zip>"
    Then verify Texas has "<reps>" local representations
    Examples:
      | product         | country       | state | zip   | reps |
      | Heating Systems | United States | Texas | 78728 | 3    |
      | Heat Trace      | United States | Texas | 78728 | 3    |
