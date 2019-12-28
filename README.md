# JEEShop

Just a simple JEE shop I made as a proyect for my school to get confident with servlets and jsp.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

First and foremost, this is a tutorial for Arch-based distro's users, if it is not your case you will need to learn how to install the packages for your distros. The rest of the tutorial is the same.

#### Eclipse

In order to run this software you will need to install Eclipse IDE for java EE developers. You can not install several eclipse versions because they will conflict so if it is your case you will need to find the way to keep both.
You cant get more info [here](https://wiki.archlinux.org/index.php/Eclipse)

```bash

	sudo pacman -Syu

	sudo pacman -S eclipse-jee


```

#### MariaDB

MariaDB is nowdays the default relational database solution for Arch Linux so it will be my choice.

```bash

	sudo pacman -Syu mariadb mariadb-clients libmariadbclient

	sudo mysql_install_db --user=mysql --basedir=/usr --datadir=/var/lib/mysql

	sudo systemctl start mysqld.service

	sudo systemctl enable mysqld.service
	
```
Now we need to create owr db. First of all we will log into owr mariadb server.

```bash
	sudo mysql -u root

```
So go ahead and create the shop's db and a new user.

```sql

	CREATE DATABASE shop;
	GRANT ALL ON shop.* TO 'shopadmin'@localhost IDENTIFIED BY 'shop';
	FLUSH privileges;
	exit
	
```

We can now login with this new user which have all privileges on that bd.

```bash
	mysql -u shopadmin -p

```

Let's create the tables we need now.

```sql
	
	use shop
	
	CREATE TABLE IF NOT EXISTS customers(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(10) NOT NULL, password VARCHAR(20) NOT NULL);
	
	CREATE TABLE IF NOT EXISTS products (idProduct INT NOT NULL PRIMARY KEY AUTO_INCREMENT, description VARCHAR(40) NOT NULL, price FLOAT(7,2) NOT NULL);
	
	
```

#### Tomcat9

### Libraries
* [MySQL's Connector/J](https://dev.mysql.com/downloads/connector/j/5.1.html) - The official connector for MySQL database which is compatible with MariaDB. More info [here](https://stackoverflow.com/questions/7592056/am-i-using-jdbc-connection-pooling)
* servlet-api.jar - Included with Tomcat (under the directory lib)
* jsp-api.jar - Included with Tomcat (under the directory lib)

### Get a copy

For some reason I can not just import this project with the git tool that eclipse provides. So I just created a new Dynamic Web Project (Dynamic web module 4.0 and named JEEShop), then I deleted the MANIFIEST.MF and I open my terminal and I start a git repository on the proyect's directory.

```bash
	
	cd eclipse-workspace/JEEShop/
	
	git init
	
	git remote add origin https://github.com/seniorglez/JEEShop.git
	
	git pull origin master
	
```

Now we have the full project but eclipse will not show the files we just pull, we just need to refresh the project. All we need to do is right click on the project and select Refresh (or press F5).


### Running it 


## Built With

* [Eclipse](https://www.eclipse.org/) - The IDE I used
* [Tomcat](http://tomcat.apache.org/) - The Java Servlet and JavaServer Pages implementation
* [MariaDB](https://mariadb.org/) - The relational database solution

## Contributing

Feel free to fork it and made pull request if I made something wrong but please keep in mind that it is just an example of servlet and jsp use.


## Authors

* **Diego Dominguez**

## License

This project is licensed under the GPL3 license - see the [LICENSE](LICENSE) file for details
