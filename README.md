# JEEShop

Just a simple JEE shop I made as a proyect for my school to get confident with servlets and jsp.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

First and foremost, this is a tutorial for Arch-based distro's users, if is not your case you will need to learn how to install the packages for your distros. The rest of the tutorial is the same.

####Eclipse

In order to run this software you will need to install Eclipse IDE for java EE developers. You can not install several eclipse versions because they will conflict so if is your case you will need to find the way to keep both.
You cant get more info [here](https://wiki.archlinux.org/index.php/Eclipse)

```bash
sudo pacman -Syu

sudo pacman -S eclipse-jee

```

####MariaDB

MariaDB is nowdays the default relational database solution for Arch Linux so it will be my choice.

```bash
sudo pacman -Syu mariadb mariadb-clients libmariadbclient

sudo mysql_install_db --user=mysql --basedir=/usr --datadir=/var/lib/mysql

sudo systemctl start mysqld.service

sudo systemctl enable mysqld.service
```
Now wee need to create owr db. First of all we will log into owr mariadb server.
```bash

sudo mysql -u root

```
So go ahead and create the shop's db

```sql
	CREATE DATABASE shop;
	GRANT ALL ON shop.* TO shopadmin@localhost IDENTIFIED BY 'shop';
	FLUSH privileges;
	exit
```

####Tomcat9

###Running it 

## Built With

* [Eclipse](https://www.eclipse.org/) - The IDE I used
* [Tomcat](http://tomcat.apache.org/) - The Java Servlet and JavaServer Pages implementation
* [MariaDB](https://mariadb.org/) - The relational database solution

## Contributing

Feel free to fork it and made pull request if i made something wrong but please keep in mind that it is just an example of servlet and jsp use.


## Authors

* **Diego Dominguez**

## License

This project is licensed under the GPL3 license - see the [LICENSE.md](LICENSE.md) file for details
