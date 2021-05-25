#@issue:CD-XXXX
Feature: example
	In order to example
	As user
	I want call example


Scenario Outline: example
  Given userId <userId> and sql <sql>
  When call example
  Then response <jsonResponse> with httpstatus <httpstatus>




  Examples:
    | userId	 							|  jsonResponse										| sql													| httpstatus	|
    | 53ca1827-cf68-4fe1-bc04-114acaf43eb4	|  json/example/response/correct1.json 	| liquibase/test/example/populate_1.sql		| 200 			|
	