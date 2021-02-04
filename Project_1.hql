CREATE DATABASE wiki_clicks;

USE wiki_clicks;

-- Question 1 

CREATE Table clicks(
	domain_code STRING,
	page_title STRING,
	count_views INT,
	total_response_size INT
)ROW FORMAT DELIMITED
FIELDS TERMINATED BY ' '
TBLPROPERTIES("skip.header.line.count"="1");

LOAD DATA LOCAL INPATH '/home/amburkee/Jan20_clickstream' INTO TABLE clicks;

SELECT page_title, SUM(count_views) AS Views 
FROM clicks
WHERE domain_code  like 'en%'
GROUP BY page_title 
ORDER BY Views DESC;

DROP TABLE clicks;

SELECT * FROM clicks;




-- Question 2 & 3 Tables 

CREATE TABLE clickstream1(
	prev STRING,
	curr STRING,
	types STRING,
	n INT
)ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t';

LOAD DATA LOCAL INPATH '/home/amburkee/wiki-dec-clickstream' INTO TABLE clickstream1;

CREATE TABLE dec_pageviews( 
	domain_code STRING,
	page_title STRING,
	count_views INT,
	total_response_size INT
)ROW FORMAT DELIMITED
FIELDS TERMINATED BY ' '
TBLPROPERTIES("skip.header.line.count"="1");

-- page views from december 1st and 15th
LOAD DATA LOCAL INPATH '/home/amburkee/dec_pageviews' INTO TABLE dec_pageviews;

SELECT * FROM clickstream1;
SELECT * FROM dec_pageviews;

DROP TABLE clickstream1;
DROP TABLE dec_pageviews;

-- Question 2

SELECT * 
FROM dec_pageviews 
WHERE domain_code like 'en%'
ORDER BY count_views DESC
LIMIT 20;

SELECT * 
FROM clickstream1 
WHERE types LIKE 'link' AND prev LIKE 'Elliot%Page'
ORDER BY n DESC
LIMIT 20;


-- Question 3

SELECT page_title, SUM(count_views) AS Views 
FROM dec_pageviews 
GROUP BY page_title HAVING page_title LIKE 'Hotel%California%'
ORDER BY Views DESC;

SELECT * 
FROM clickstream1 
WHERE types LIKE 'link' AND prev LIKE 'Hotel%California'
ORDER BY n DESC
LIMIT 20;

SELECT page_title, SUM(count_views) AS Views 
FROM dec_pageviews 
GROUP BY page_title HAVING page_title LIKE 'Hotel_California_(Eagles_album)'
ORDER BY Views DESC;

SELECT * 
FROM clickstream1 
WHERE types LIKE 'link' AND prev LIKE 'Hotel_California_(Eagles_album)'
ORDER BY n DESC
LIMIT 20;

SELECT page_title, SUM(count_views) AS Views 
FROM dec_pageviews 
GROUP BY page_title HAVING page_title LIKE 'The_Long_Run_(album)'
ORDER BY Views DESC;

SELECT * 
FROM clickstream1 
WHERE types LIKE 'link' AND prev LIKE 'The_Long_Run_(album)'
ORDER BY n DESC
LIMIT 20;



-- Question 4

CREATE TABLE clickstream2(
	domain_code STRING,
	page_title STRING,
	count_views INT,
	total_response_size INT
)ROW FORMAT DELIMITED
FIELDS TERMINATED BY ' '
TBLPROPERTIES("skip.header.line.count"="1");

LOAD DATA LOCAL INPATH '/home/amburkee/9_5_London' INTO TABLE clickstream2;

SELECT page_title, SUM(count_views) AS Views 
FROM clickstream2 
WHERE domain_code like 'en%'
GROUP BY page_title 
ORDER BY Views DESC;

CREATE TABLE clickstream2_b( 
	domain_code STRING,
	page_title STRING,
	count_views INT,
	total_response_size INT
)ROW FORMAT DELIMITED
FIELDS TERMINATED BY ' '
TBLPROPERTIES("skip.header.line.count"="1");

LOAD DATA LOCAL INPATH '/home/amburkee/9_5_America' INTO TABLE clickstream2_b;

SELECT page_title, SUM(count_views) AS Views 
FROM clickstream2_b 
WHERE domain_code like 'en%'
GROUP BY page_title 
ORDER BY Views DESC;

DROP TABLE clickstream2;
DROP TABLE clickstream2_b;




-- Question 5
CREATE TABLE click5 (  
	wiki_db	string,	
	event_entity	string,
	event_type	string,
	event_timestamp	string,
	event_comment	string,
	event_user_id	bigint,	
	event_user_text_historical	string,	
	event_user_text	string,	
	event_user_blocks_historical	array<string>,	
	event_user_blocks	array<string>,	
	event_user_groups_historical	array<string>,	
	event_user_groups	array<string>,	
	event_user_is_bot_by_historical	array<string>,	
	event_user_is_bot_by	array<string>,	
	event_user_is_created_by_self	boolean,
	event_user_is_created_by_system	boolean,	
	event_user_is_created_by_peer	boolean,	
	event_user_is_anonymous	boolean,	
	event_user_registration_timestamp	string,	
	event_user_creation_timestamp	string,	
	event_user_first_edit_timestamp	string,	
	event_user_revision_count	bigint,	
	event_user_seconds_since_previous_revision	bigint,	
	page_id	bigint,	
	page_title_historical	string,	
	page_title	string,	
	page_namespace_historical	int,	
	page_namespace_is_content_historical	boolean,	
	page_namespace	int,	
	page_namespace_is_content	boolean,	
	page_is_redirect	boolean,	
	page_is_deleted	boolean,	
	page_creation_timestamp	string,
	page_first_edit_timestamp	string,	
	page_revision_count	bigint,	
	page_seconds_since_previous_revision	bigint,	
	user_id	bigint,	
	user_text_historical	string,	
	user_text	string,	
	user_blocks_historical	array<string>,	
	user_blocks	array<string>,	
	user_groups_historical	array<string>,	
	user_groups	array<string>,	
	user_is_bot_by_historical	array<string>,	
	user_is_bot_by	array<string>,	
	user_is_created_by_self	boolean,	
	user_is_created_by_system	boolean,	
	user_is_created_by_peer	boolean,	
	user_is_anonymous	boolean,	
	user_registration_timestamp	string,	
	user_creation_timestamp	string,	
	user_first_edit_timestamp	string,	
	revision_id	bigint,	
	revision_parent_id	bigint,	
	revision_minor_edit	boolean,	
	revision_deleted_parts	array<string>,	
	revision_deleted_parts_are_suppressed	boolean,	
	revision_text_bytes	bigint,	
	revision_text_bytes_diff	bigint,	
	revision_text_sha1	string,	
	revision_content_model	string,
	revision_content_format	string,	
	revision_is_deleted_by_page_deletion	boolean,	
	revision_deleted_by_page_deletion_timestamp	string,	
	revision_is_identity_reverted	boolean,	
	revision_first_identity_reverting_revision_id	bigint,	
	revision_seconds_to_identity_revert	bigint,
	revision_is_identity_revert	boolean,
	revision_is_from_before_page_creation	boolean,
	revision_tags	array<string>
)ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
TBLPROPERTIES("skip.header.line.count"="1");

LOAD DATA LOCAL INPATH '/home/amburkee/enwiki_Revision' INTO TABLE click5;

SELECT * FROM click5;

SELECT event_comment, revision_seconds_to_identity_revert, revision_is_identity_reverted
FROM click5
WHERE event_comment like '%vand%' AND revision_is_identity_reverted = true;

SELECT AVG(revision_seconds_to_identity_revert) as Average_Seconds_to_Revert
From click5
Where revision_seconds_to_identity_revert IN(
	SELECT revision_seconds_to_identity_revert
	FROM click5
	WHERE event_comment like '%vand%' AND revision_is_identity_reverted = true AND revision_seconds_to_identity_revert BETWEEN 0 AND 10000
);

-- 9-5 American views
SELECT SUM(count_views) as View
FROM clickstream2_b;

DROP TABLE click5;




-- Question 6 - Where are people more likely to come from?

SELECT * 
FROM clickstream1 
ORDER BY n DESC;

SELECT types, SUM(n) AS Number_of_Link_Clicks
FROM clickstream1
GROUP BY types;