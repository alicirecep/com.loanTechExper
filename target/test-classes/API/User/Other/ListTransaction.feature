Feature: As a user, I want to access the user list transactions through API connection.

  Scenario: When a GET request with valid authorization information is sent to the user/list/transaction
  endpoint, the returned status code should be 200, and the response remark should be verified as "success"

    Given The API user sets "user/list/transaction" path parameters
    And The API user records the response returned from the user list transaction endpoint
    Then The API user verifies that the status code is 200
    And The API user verifies that the remark information in the response body is "success"


  Scenario Outline: Verify the information returned in the response for the entity with id(x)
  (user_id, amount, charge, post_balance, trx_type, trx, details, remark, created_at, updated_at)

    Given The API user sets "user/list/transaction" path parameters
    And The API user records the response returned from the user list transaction endpoint
    Then Verify the information of the one with the index <dataIndex> in the API user response body: <user_id>, "<amount>", "<charge>", "<post_balance>", "<trx_type>", "<trx>", "<details>", "<remark>", "<created_at>", "<updated_at>"

    Examples:
      | dataIndex | user_id | amount        | charge     | post_balance  | trx_type | trx          | details    | remark     | created_at                  | updated_at                  |
      | 0         | 11      | 3000.00000000 | 0.00000000 | 7695.00000000 | +        | DDAMY4BDT6KQ | Loan taken | loan_taken | 2023-12-14T15:06:50.000000Z | 2023-12-14T15:06:50.000000Z |