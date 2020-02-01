# JEEShop

Just a simple JEE shop I made as a proyect for my school to get confident with servlets and jsp.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

First and foremost, this is a tutorial for Arch-based distro's users, if it is not your case you will need to learn how to install the packages for your distros. The rest of the tutorial is the same.

#### JDK 8 or higher

I hope you did not need help to install this. Anyway [here](https://wiki.archlinux.org/index.php/Java#Installation) is the archwiki page about java. If you are not familiar with JDK8 new features please check this [CheatSheet](https://github.com/BafS/Java8-CheatSheet/blob/master/README.md) out.

In my case I will choose [openJDK11](https://www.archlinux.org/packages/extra/x86_64/jdk11-openjdk/) because is the LTS version of the JDK.

```bash

	sudo pacman -Syu
	
	sudo pacman -S jdk11-openjdk

```
Now we need to set the JAVA_HOME in my case I will set it for all users so I just add this at the end of /etc/profile 

```bash
	
	#JAVA
	
	export JAVA_HOME="/usr/lib/jvm/java-11-openjdk"

	export PATH=$JAVA_HOME/bin:$PATH

```
In case you would prefer to set the JAVA_HOME only for your user just add the same lines on ~/.bash_profile If you want to know more about the bash configuration files please check the [wiki](https://wiki.archlinux.org/index.php/Bash)

After that restart your machine.

```bash

	reboot

```

#### Eclipse

In order to run this software you will need to install Eclipse IDE for java EE developers. You can not install several eclipse versions because they will conflict so if it is your case you will need to find the way to keep both.
You cant get more info [here](https://wiki.archlinux.org/index.php/Eclipse).

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
	
	CREATE TABLE IF NOT EXISTS customers(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(10) NOT NULL,
	password VARCHAR(20) NOT NULL);
	
	CREATE TABLE IF NOT EXISTS products (
	idProduct INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	description VARCHAR(40) NOT NULL, 
	price FLOAT(7,2) NOT NULL);
	
	CREATE TABLE IF NOT EXISTS bills (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	client_id INT NOT NULL,
	purchase_date DATETIME NOT NULL);
	
	CREATE TABLE IF NOT EXISTS bill_lines(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	bill_id INT NOT NULL,
	product_id INT NOT NULL,
	units INT NOT NULL CHECK( UNITS > 0));
	
```

#### Tomcat9

Tomcat is the server that will allow us to execute owr projet.In order to install Tomcat just download it form [here](https://tomcat.apache.org/download-90.cgi) or use wget.

If you have download the tar.gz version execute this commands to extract it and move it to your home directory.

```bash

	cd ~/Downloads
	
	tar -xvzf apache-tomcat-9.x.xx.tar.gz
	
	mv apache-tomcat-9.x.xx ~/Tomcat	

```

Now we need to change the file permissions under the directory bin to allow Eclipse to execute Tomcat

```bash

	sudo chmod +x ~/Tomcat/bin/*

```
This Tomcat installation is only for development and testing purposes, so if you want to use Tomcat to deploy your JavaEE application you will need to follow a few more steps that I will add on an md file on the future.

### Libraries
* [MySQL's Connector/J](https://dev.mysql.com/downloads/connector/j/5.1.html) - The official connector for MySQL database which is compatible with MariaDB. More info [here](https://stackoverflow.com/questions/7592056/am-i-using-jdbc-connection-pooling)
* servlet-api.jar - Included with Tomcat (under the directory lib)
* jsp-api.jar - Included with Tomcat (under the directory lib)

### Get a copy

For some reason I can not just import this project with the git tool that eclipse provides. So I just created a new Dynamic Web Project (Dynamic web module 4.0 and named JEEShop), then I deleted the MANIFIEST.MF and I open my terminal and I start a git repository on the proyect's directory.

```bash
	
	cd eclipse-workspace/JEEShop
	
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

* **Diego Dominguez**   <a href="https://twitter.com/DGlez1111" target="_blank">
    <img alt="Twitter: DGlez1111" src="https://img.shields.io/twitter/follow/DGlez1111.svg?style=social" />
  </a>

## License

JEEShop is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or any later version.

JEEShop is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the [GNU General Public License](LICENSE)
along with JEEShop. If not, see [https://www.gnu.org/licenses/](https://www.gnu.org/licenses/)

![GPL3 or later](https://www.gnu.org/graphics/gplv3-or-later.png)

### Why GPL3 and not AGPL3?

I used GPL3 license because this is just an example of app and it will never turn on a real app because if you want to develop an real one you will prefer to use a framework like [Hibernate](https://hibernate.org/). But if you are developing a server side application under GPL3 means that this app will provide HTML and not an executable program so another person could take your code, make changes on it and not publish his own version. If you want to keep your application full free please use [AGPL3](https://www.gnu.org/licenses/agpl-3.0.en.html) instead of GPL3.
