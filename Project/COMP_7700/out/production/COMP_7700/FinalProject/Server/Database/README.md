# Database Container


## Running locally
There are 2 possibly ways to run locally:


### With the Server Container
From the root of the repo do the following:

```bash
mkdir ~/data
docker compose up -d
```

This will start both the Server Container and Database Container as well as manage all resources.

To stop the containers and remove them, type the following:

```bash
docker compose down && docker image rm project-database:latest && TODO
```

This will clean auto clean the resources as well.


### Without the Server Container
From the root of the repo do the following:

```bash
mkdir ~/data
docker build -t project-database src/Database/
docker network create project_default
docker run --detach --network=project_default --volume=<path/to/home>/data:/var/lib/mysql --restart=always --env-file=src/Database/mysql_variables.env --name=database project-database:latest
```

To leave mysql:

```mysql
exit
```

To clean up the resources:

```bash
docker stop database
docker network rm project_default
docker image rm project-database:latest
```


## Testing
Once you have a container up, you can check that it works by creating another container that will communicate to it through the local network:

```bash
docker run -it --network project_default --rm mysql mysql --hdatabase -uroot -p
```

Then entering the password - `Passw0rd1!`

You can then query the database
