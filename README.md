# katabank
Dans ce fichier je vais en place quelques stratégies de tests des users stories de Katabank

# Link to Retrieve all accounts in my bank
http://localhost:8080/accounts

# Link to Retrieve all operations in these accounts
http://localhost:8080/operations

# Link to Retrieve all operations in a specific account
http://localhost:8080/accounts/ACCT0002/operations

avec {ACCT0002} le numéro du compte qu'on veut afficher ses opérations

# Deposit or withdrawing amount in an account
Pour faire : 
- on doit lancer un client Postman
- Changer la méthode HTTP à PUT
- Clic sur Body and check RAW
- Ajouter l'objet JSON suivant dans le champ de saisie:
{
        "accountNumber": "ACCT0002",
        "amount": 1150
}
Ici ACCT0002 est le numéro du compte qu'on veut créditer ou débiter et 1150 le montant.
On peut changer ce numéro pour faire plus d'opérations.
NB : Le numéro doit exister dans notre liste statique sinon l'application va générer une exception
- In the SelectOneMenu item on doit choisir JSON. Le format XML n'est pas pris en compte dans notre cas.
- Ajouter l'URL suivant pour créditer ou débiter : 
http://localhost:8080/accounts/deposit
http://localhost:8080/accounts/withdrawal
- Puis cliquer sur SEND ou ENVOYER

# J'ai mis en place un système d'Hyperlink pour retrouver facilement les URI et pouvoir tester rapidement
Pour ce faire, essayer d'afficher URI GET account et on aura tout.
L'URI est le suivant : http://localhost:8080/accounts/ACCT0002/ avec ACCT0002 le numéro du compte correspondant.
NB : On peut saisir cette URI soit sur Postman soit sur un navigateur
