JAthenaeum
==========

![JAthenaeum](https://raw.github.com/JCPP/JAthenaeum/master/WebContent/image/logoonlybook.png)

Java Web Application to handle a library.


Traccia
-------
Realizzare un’applicazione per la gestione di una biblioteca. L’applicazione deve gestire l’elenco dei libri posseduti dalla biblioteca e le prenotazioni degli stessi. Ogni libro è identificato da autore, titolo, genere, codice ISBN, breve descrizione e immagine della copertina. Possono esserci più copie dello stesso libro. Ogni autore è identificato da nome, cognome, data di nascita, biografia, foto. Ogni prenotazione è identificata dal nominativo che ha effettuato il prestito e suoi riferimenti, dalla data di inizio e fine prestito e dal libro prestato. Le specifiche sono:
  * Riconoscimento dell’utente tramite login (ad esempio con utenze predefinite);
  * Gestione autori: inserimento, modifica, ricerca, cancellazione;
  * Gestione libri: inserimento, modifica dati, ricerca (con visualizzazione copie totali e presenti in biblioteca), caricamento di copie nella libreria;
  * Prenotazione libri: inserimento, modifica, ricerca, cancellazione, inserimento del rientro del libro, visualizzazione elenco prestiti scaduti;
  * Logout.


Documentation
-------------

### Database ###

I have stated that there are users who manage the library and they have the complete control on authors, books customers and loans. When a customer wants to borrow a book, it asks to a user (that has a pc) it and the user provides to add the loan. A similar operation will be done when the book is returned. If the customer hasn't a card, user needs to create a new one with its information. 
The user is also responsible for managing the books and authors.

Given that a book can have more than one copy, I decided to create the copy entity.

The **E-R model** is the following:

![E-R model](https://raw.github.com/JCPP/JAthenaeum/master/doc/database/export/E-R%20Model.png)


This is the **Extensive Logical Schema**:


| Table Name    | Field Name         | Type    | Size | Primary | Foreign Key | Not Null | Description           |
| ------------- |:------------------:| -----:  |-----:|--------:|------------:|---------:|----------------------:|
| **User**      | UserID             | Integer | 11   | Yes     | No          | No       |                       |
|               | UserEmail          | String  | 50   | No      | No          | Yes      |                       |
|               | UserPassword       | String  | 50   | No      | No          | Yes      |                       |
|               | UserName           | String  | 50   | No      | No          | No       |                       |
|               | UserSurname        | String  | 50   | No      | No          | No       |                       |
|               | UserBornDate       | Date    | 10   | No      | No          | No       |                       |
| **Customer**  | CustomerCardNumber | Integer | 11   | Yes     | No          | No       |                       |
|               | CustomerEmail      | String  | 50   | No      | No          | Yes      |                       |
|               | CustomerName       | String  | 50   | No      | No          | Yes      |                       |
|               | CustomerSurname    | String  | 50   | No      | No          | Yes      |                       |
| **Book**      | BookID             | Integer | 11   | Yes     | No          | No       |                       |
|               | BookTitle          | String  | 50   | No      | No          | Yes      |                       |
|               | BookCover          | String  | 200  | No      | No          | No       | The path to the cover |
|               | BookGenre          | String  | 50   | No      | No          | No       |                       |
|               | BookIsbnCode       | String  | 13   | No      | No          | No       |                       |
|               | BookDescription    | String  | 500  | No      | No          | No       |                       |
| **Copy**      | CopyID             | Integer | 11   | Yes     | No          | No       |                       |
|               | BookID             | Integer | 11   | No      | Yes         | Yes      |                       |
| **Loan**      | LoanID             | Integer | 11   | Yes     | No          | No       | Customer ↔ Copy       |
|               | CustomerCardNumber | Integer | 11   | No      | Yes         | Yes      |                       |
|               | CopyID             | Integer | 11   | No      | Yes         | Yes      |                       |
|               | LoanStartDate      | Date    | 10   | No      | No          | Yes      |                       |
|               | LoanEndDate        | Date    | 10   | No      | No          | Yes      |                       |
|               | LoanReturned       | Boolean |      | No      | No          | Yes      |                       |
| **Author**    | AuthorID           | Integer | 11   | Yes     | No          | No       |                       |
|               | AuthorName         | String  | 50   | No      | No          | Yes      |                       |
|               | AuthorSurname      | String  | 50   | No      | No          | Yes      |                       |
|               | AuthorPhoto        | String  | 200  | No      | No          | No       | The path to the photo |
|               | AuthorBornDate     | Date    | 10   | No      | No          | No       |                       |
|               | AuthorBiography    | String  | 1000 | No      | No          | No       |                       |
| **Writes**    | WritesID           | Integer | 11   | Yes     | No          | No       | Author ↔ Book         |
|               | BookID             | Integer | 11   | No      | Yes         | Yes      |                       |
|               | AuthorID           | Integer | 11   | No      | Yes         | Yes      |                       |



### UML Diagrams ###

For best viewing I preferred to divide the diagrams.

#### Author ####

![Author UML Diagram](https://raw.github.com/JCPP/JAthenaeum/master/doc/classes%20diagram/generated/Author.png)


#### Book - Copy- Writes ####

![Book - Copy - Writes UML Diagram](https://raw.github.com/JCPP/JAthenaeum/master/doc/classes%20diagram/generated/Book-Copy-Writes.png)


#### Customer ####

![Customer UML Diagram](https://raw.github.com/JCPP/JAthenaeum/master/doc/classes%20diagram/generated/Customer.png)


#### Loan ####

![Loan UML Diagram](https://raw.github.com/JCPP/JAthenaeum/master/doc/classes%20diagram/generated/Loan.png)


#### User ####

![User UML Diagram](https://raw.github.com/JCPP/JAthenaeum/master/doc/classes%20diagram/generated/User.png)




### Libraries ###

#### Java side ####

The project uses some libraries to simplify and facilitate the work for the creation of the web application:

* Struts;
* MySql connector.

For a more precise and detailed list, please take a look at the [ivy.xml](https://github.com/JCPP/JAthenaeum/blob/master/ivy.xml) file.

**Note**: most of the libraries that are downloaded after running the build, are dependencies of the libraries mentioned above.


#### Front-End side #####

The project uses [bootstrap](http://getbootstrap.com/), the most popular front-end framework for developing responsive, mobile first projects on the web.



### Pages ###

For simplicity will be shown an example of a single operation.

#### Struts Config ####

This project uses a *struts-config.xml* for the configurations of the framework.
It has a **form-bean** for each **element**.
For example **user** is defined as follows:

```xml
<!-- User -->
<form-bean name="userForm" type="com.github.jcpp.jathenaeum.beans.UserForm"/>
```

All the action (apart from the index) are managed using a dispatch action. I prefer it because I can centralize all requests and avoid generating redundant code for similar actions.

I added another division between the action that show a form and the action that instead analyze the data and add/update/delete items in the database.


Here are the actions for the user:

```xml
<!-- User -->
<action path="/user" type="com.github.jcpp.jathenaeum.actions.UserAction" parameter="op">
 <forward name="signup" path="page.user.signup"/>
 <forward name="login" path="page.user.login"/>
 <forward name="logoutSuccess" path="page.user.logout.confirm"/>
 <forward name="logoutFailed" path="page.user.logout.failed"/>
 <forward name="alreadyLogged" path="page.user.login.already"/>
 <forward name="loginRequired" path="page.user.login"/>
</action>

<!-- User Operations -->
<action
 path="/doUser"
 type="com.github.jcpp.jathenaeum.actions.UserActionDo"
 name="userForm"
 scope="request"
 validate="false"
 parameter="op">
 <!-- Sign Up -->
 <forward name="signupErrors" path="/user.do?op=signup" redirect="true"/>
 <forward name="signupSuccess" path="page.user.signup.confirm"/>
 <forward name="signupFailed" path="page.user.signup.failed"/>
 
 <!-- Login -->
 <forward name="loginErrors" path="/user.do?op=login" redirect="true"/>
 <forward name="loginSuccess" path="page.user.login.confirm"/>
 <forward name="loginFailed" path="page.user.login.failed"/>
 
 <!-- Errors -->
 <forward name="alreadyLogged" path="page.user.login.already"/>
</action>
```

The dispatch action uses the **op** parameter to differentiate one from another operation.


#### Actions #####

As mentioned earlier, for each item I preferred to manage two action: one for operations and write/update/delete and to display the form and any input errors. 
**But why?**

* the form elements of struts are not versatile:
   * you can't add custom attribute and bootstrap do it sometimes (like **required**, **placeholder** or **autofocus**);
   * you can't use the **date** type for **input** elements;
   * restricts your freedom.


##### Forms #####

As an example see **UserAction.signup(...)**:


```java
public ActionForward signup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	String actionTarget = null;
	
	HttpSession session = request.getSession();
	if(Validator.isLogged(session)){
		return mapping.findForward("alreadyLogged");
	}

	ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
	UserForm userForm = (UserForm) session.getAttribute("form");
	
	if(actionErrors != null){
		//Save the errors in this action
		saveErrors(request, actionErrors);
	}
	
	//Remove attributes from session
	session.removeAttribute("errors");
	session.removeAttribute("form");
	

	//Set the request
	request.setAttribute("signupForm", userForm);
	actionTarget = "signup";
	
	return mapping.findForward(actionTarget);
}
```

It first check if the user is already logged in and in this case redirect him to the **alreadyLogged** page. Now read all errors and the form from session. If actionErrors is not equal to null, save errors within the action. 
This step is done to allow struts to save all the errors and then display them using:

```html
<html:errors/>
```

At this point it can remove all saved objects from the session. Then we set the form to allow struts to use bean or jsp native print. As a last step, it redirects the user to **signup** forward.



##### Operations #####

As an example see **UserActionDo.signup(...)**:


```java
public ActionForward signup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	String actionTarget = null;
	
	HttpSession session = request.getSession();
	if(Validator.isLogged(session)){
		return Redirector.alreadyLoggedRedirect(mapping, session);
	}
	
	UserForm uf = (UserForm) form;
	
	ActionErrors actionErrors = uf.validateSignUp(mapping, request);
	
	//If there are some errors, redirect to the form page
	if(!actionErrors.isEmpty()){
		actionTarget = "signupErrors";
		
		session.setAttribute("errors", actionErrors);
		session.setAttribute("form", uf);
		
		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
		return redirect;
	}
	
	User user;
	
 if(form != null){
 	user = new User(uf);
 	
 	try{
 		if(UserDAO.register(user)){
 			actionTarget = "signupSuccess";
 		}
 		
 		//Auto login
 		session.setAttribute("user", user);
 		
 	}catch(Exception e){
 		actionTarget = "signupFailed";
 	}
 }
 else{
 	actionTarget = "signupFailed";
 }

	return mapping.findForward(actionTarget);
}
```

It first check if the user is already logged in and in this case redirect him to the **alreadyLogged** page. Now read the form and validate it. If it founds some errors, redirect the user to the form page. If the form is not null, then try to register the new user and auto login. If a problem arises during the registration, the the user will be redirect in an error page, otherwise, it will be informed of the success.


#### Tiles ####

This project uses [struts tiles](https://github.com/JCPP/JAthenaeum/blob/master/WebContent/WEB-INF/tiles-defs.xml) to manage the pages.
Ultimately, all the pages inherit the characteristics of **base.definition.bootstrap**, defined thus:

```xml
<!-- Base Tiles Bootstrap Definition -->
<definition name="base.definition.bootstrap" path="/layout/bootstrap-layout.jsp">
 <put name="style" value="/css/style.css" />
 <put name="jumbotronEnabled" value="false" />
 <put name="navbarEnabled" value="true" />
 <put name="footerEnabled" value="true" />
 <put name="redirect" value="" />
 <put name="head" value="/layout/bootstrap-head.jsp" />
 <put name="jumbotron" value="/layout/bootstrap-jumbotron.jsp" />
 <put name="navbar" value="/layout/bootstrap-navbar.jsp" />
 <put name="footer" value="/layout/bootstrap-footer.jsp" />
</definition>
```

where:

* **style** defines the css file to load;
* **jumbotronEnabled** defines if the Jumbotron element must be shown;
* **navbarEnabled** defines if the navbar element must be shown;
* **footerEnabled** defines if the footer element must be shown;
* **redirect** defines the page where you'll be redirect in 3 seconds (if the value is empty, this will not be considered);
* **head** defines the page to load for the head;
* **jumbotron** defines the page to load for the jumbotron element;
* **navbar** defines the page to load for the navbar element;
* **footer** defines the page to load for the footer element.

At this point, each page extends that stated above, greatly simplifying the statement.
I used a standard for naming pages: page.**element**.**operation**.**result**, where:
* **element** defines the element on which you are doing the operation (for example **user** or **customer**);
* **operation** defines the operation (for example **signup** or **add**);
* **result** (optional) defines the result of the operation (for example: **failed** or **confirm**).

You can take a look at the declaration of the pages to the functionality of the signup:

```xml
<!-- Sign up page -->
<definition name="page.user.signup" extends="base.definition.bootstrap">
 <put name="title" value="Signup" />
 <put name="body" value="/layout/user/signup.jsp" />
 <put name="style" value="css/signup.css" />
</definition>

<!-- Failed sign up -->
<definition name="page.user.signup.failed" extends="base.definition.bootstrap">
 <put name="title" value="Failed Signup" />
 <put name="body" value="/layout/user/failedSignup.jsp" />
</definition>

<!-- Confirm sign up -->
<definition name="page.user.signup.confirm" extends="base.definition.bootstrap">
 <put name="redirect" value="index.do" />
 <put name="title" value="Confirm Signup" />
 <put name="body" value="/layout/user/confirmSignup.jsp" />
</definition>
```

**page.user.signup** defines a custom style, while **page.user.signup.confirm** defines a redirect after the signup.


Now take a look at signup.jsp:

```html
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:errors/>

<form class="form-signup" role="form" action="doUser.do?op=signup" method="post" name="signupForm">
	<h2 class="form-signup-heading">Sign Up</h2>
	<input type="email" name="email" class="form-control" placeholder="Email address" value="${signupForm.email}" required autofocus>
	<input type="password" name="password" class="form-control" placeholder="Password" required>
	<input type="password" name="passwordControl" class="form-control" placeholder="Retype Password" required>
	<input type="text" name="name" class="form-control" placeholder="Name" value="${signupForm.name}">
	<input type="text" name="surname" class="form-control" placeholder="Surname" value="${signupForm.surname}">
	<input type="date" name="bornDate" class="form-control" placeholder="01/02/1970" value="${signupForm.bornDate}">
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
</form>
```

It prints all the errors if any and show the form with the value set by **UserAction**. This is the only page that has different content of all the layout (apart from the definition of tiles that can avoid showing for example the jumbotron).



##### Structure #####

The pages are divided into sub-folders based on the element. All the **.jsp** files are in the WebContent/layout directory. Here we can also find the files that build the layout.



### Tools ###

The project uses some tools to simplify and facilitate the work for the creation of the web application:

* [Eclipse](http://eclipse.org/): an integrated development environment (IDE);
* [Ivy](http://ant.apache.org/ivy/): The agile dependency manager.



What you need
-------------

### Eclipse Side ###

* Java EE Eclipse or Eclipse with all the necessary plugin for Java EE;
* IvyDE;
* Tomcat v.6;
* Set the build path from **Build Path** -> **Configure Build Path...**
   * add JDK;
   * add Tomcat server.
* **Ivy** -> **Resolve**;
* Set Ant property: Window -> Preferences -> Ant -> Runtime -> Properties -> Add Property ->
  * Name: **basedir**
  * Value: **${project_loc}**
* Set **Targeted Runtimes** for the project;
* Press **F5** on the project name in the **Project Explorer**;


### MySql Side ###

* Start the MySql service;
* Edit the **WebContent/META-INF/context.xml** file with your info (username and password);
* Create the database using the **sql/query.sql** file;
* Add the example data using the **sql/insert.sql** file.


How it works
------------

1. Import in Eclipse;
2. Run build.xml (with **retrieve** option);
3. Go to [url](http://localhost:8080/JAthenaeum/index.do) (if your server is listening on 8080 port);
4. Laugh hysterically.


### Let's get into JAthenaeum ###

The application uses a system with limited access: only registered users can access the system. You are free to create your own account and use it to login. If you entered the example data, you will be able to login using:
**test@test.it** and **test**.

After login you will be redirect to the home page that will show you the operations that you can do. In reality, these are not all because some of them are related to a single element.


#### Home Page ####

![Home Page](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/HomePage.png)



#### Author ####


##### Add #####

![Add Author](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/AddAuthor.PNG)


##### Edit #####

![Edit Author](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/EditAuthor.PNG)


##### Delete #####

![Delete Author](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/DeleteAuthor.PNG)


##### View All #####

![View All Authors](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/ViewAllAuthors.PNG)


##### Search #####

![Search Authors](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/SearchAuthors.PNG)




#### Book ####


##### Add #####

![Add Book](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/AddBook.PNG)


##### Edit #####

![Edit Book](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/EditBook.PNG)


##### Delete #####

![Delete Book](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/DeleteBook.PNG)


##### Manage Copies #####

![Manage Book Copies](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/ManageBookCopies.PNG)


##### View All #####

![View All Books](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/ViewAllBooks.PNG)


##### Search #####

![Search Books](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/SearchBooks.PNG)




#### Customer ####


##### Add #####

![Add Customer](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/AddCustomer.PNG)


##### Edit #####

![Edit Customer](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/EditCustomer.PNG)


##### Delete #####

![Delete Customer](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/DeleteCustomer.PNG)


##### View All #####

![View All Customers](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/ViewAllCustomers.PNG)




#### Loan ####


##### Add #####

![Add Loan](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/AddLoan.PNG)


##### Edit #####

![Edit Loan](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/EditLoan.PNG)


##### Delete #####

![Delete Loan](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/DeleteLoan.PNG)


##### View All #####

![View All Loans](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/ViewAllLoans.PNG)


##### View All Expired #####

![View All Expired Loans](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/ViewAllExpiredLoans.PNG)


##### Search #####

![Search Loans](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/SearchLoans.PNG)




#### User ####


##### Login #####

![User Login](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/UserLogin.PNG)


##### Sign Up #####

![User Sign Up](https://raw.github.com/JCPP/JAthenaeum/master/doc/screenshots/UserSignUp.PNG)

