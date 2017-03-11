require 'rubygems'
require 'dbi'
require 'mysql'




begin
     # connect to the MySQL server
     dbh = DBI.connect('DBI:Mysql:Ruby_db:localhost', 'root', 'mysql')
     # get server version string and display it
     row = dbh.select_one("SELECT * FROM client")
     puts "Server version: " + row[1]
rescue DBI::DatabaseError => e
     puts "An error occurred"
     puts "Error code:    #{e.err}"
     puts "Error message: #{e.errstr}"
ensure
     # disconnect from server
     dbh.disconnect if dbh
end