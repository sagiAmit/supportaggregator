# Support Aggregator

This system connects to CRM server and save the data in a database
for local viewing. It aggregates the data from both Banana and Strawberry CRM systems.

The api for the system is <SERVER>:<port>/supportaggregator/cases
it returns all the cases aggregated by error provider and status.


The implementation is minimal and meant for extension - The data is saved aggregated, and it will
allow an addition of filters on the backend side quite simply. 
In the meantime if filtering is critical it can be performed (suboptimally ) on the front end side
as most data display libs support filtering.

The system can be broken into 2 different services - the db updater and the aggregation reader.

The db updater runs every 4 hours by default (can be configured) and update the database - calculate the aggregations
and save all the cases.
The reader is activated via the api and returns the saved aggregated data.

The system requires a MySQL database - the details can be configured in the application.properties file.

To build the system run ./mvnw clean package
and the executable jar can be runned via java -jar target/supportaggregator-0.0.1-SNAPSHOT.jar



