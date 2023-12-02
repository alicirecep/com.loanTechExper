Feature: As a user, I want to be able to access the Ticket List via API connection.

  Scenario: When a GET request is sent to the user/ticket/list endpoint with valid authorization
            information, it must be verified that the status code returned is 200 and the response
            remark information is "success".

    Given API user sets "user/ticket/list" path parameters
    And API user saves the response from the user ticket list endpoint
    Then API user verifies status code is 200
    And API user verifies that the remark information in the response body is "success"


  Scenario Outline: Verify the information (user_id, name, email, ticket, subject, status, priority,
                    last_reply, created_at, updated_at) returned in the Response for the id(x)

    Given API user sets "user/ticket/list" path parameters
    And API user saves the response from the user ticket list endpoint
    Then API kullanici response bodydeki idsi <dataIndex> olanın <user_id>, "<name>", "<email>", "<ticket>", "<subject>", <status>, <priority>, "<last_reply>", "<created_at>", "<updated_at>" bilgilerini doğrular

    Examples:
    | dataIndex | user_id | name | email | ticket | subject | status | priority | last_reply | created_at | updated_at |
    | 2 | 4 | test_Ozge Last_ozge | ozge@gmail.com | 285884 | Test Ticket | 0 | 0 | 2023-11-25 16:19:55 | 2023-11-25T21:19:55.000000Z | 2023-11-25T21:19:55.000000Z |