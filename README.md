# snippet-manager

This is the API to get, create, edit and save snippets.

## Functionalities:
create snippet

update snippet

get snippet list (usa el share para obtener la lista de snippets id que tiene el usuario que le compartieron)
		 (tambien usa el snippet runner para ver si el linteo es bueno o no)

see snippet (tambien usa el snippet runner para ver si el linteo es bueno o no)

add test case

setLintingRules

setFormatingRules

getLintingRules

getFormatRules 

## List of endpoints
* GET All snippets: http://localhost:8080/snippets
* GET Snippet by Id: http://localhost:8080/snippets/{snippet-id}
* GET Snippets by user Id: http://localhost:8080/snippets/{user-id}
* GET User's and shared snippets by id: http://localhost:8080/snippets/all/{user-id}
* POST create snippet: http://localhost:8080/snippets/
* PUT Edit snippet: http://localhost:8080/snippets/{snippet-id}/
* DETLETE delete snippet: http://localhost:8080/snippets/{snippet-id}/
* POST create test case: http://localhost:8080/snippets/test/
* DELETE delete test case - http://localhost:8080/snippets/test/{test-id}
* PUT edit test case -  http://localhost:8080/snippets/test/{test-id}
* PUT edit user's lint rules - http://localhost:8080/snippets/lint/:userId
* GET get user's lint rules - http://localhost:8080/snippets/lint/:userId
* PUT edit user's format rules - http://localhost:8080/snippets/format/:userId
* GET get user's format rules -  http://localhost:8080/snippets/format/:userId

