Feature: Validating add Place API

@AddPlace @Regression
Scenario Outline: Verifying place is added successfully using AddPlaceAPI
Given Add place payload with "<name>" "<address>" "<language>"
When User calls "AddPlaceAPI" using "POST" http request
Then Add API is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
      |name       | address| language |
      |AAWarehouse| QVB    | Spanish  |
      |BBwarehouse|World TC| English  |

@DeletePlace @Regression
Scenario: Verify delete place functionality is working
Given Delete place payload
When User calls "deletePlaceAPI" using "POST" http request
Then Add API is success with status code 200
And "status" in response body is "OK"