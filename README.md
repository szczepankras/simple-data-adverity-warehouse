# simple-data-adverity-warehouse

Hey guys

Thank you for interesting challenge. Below guide and conclusions.

<h2>Demo:</h2>

https://www.youtube.com/watch?v=ciuQhwM48z8

You can play with the service by your own. Check links in the email from me as this file is public and my "heroku-dyno" instance won't survie any DDoS :grinning:.
<h3> Demo notes </h3>
<ul>
 <li> Watch above YouTube live demo first </hi>
 <li> I highly recommend to use "playground" which is deployed with the project. It has nice suggestions. Of course you can use any http client like Postman as well (link also provided in the email). </li>	
 <li> 'Swagger' documentation is on the tab "schema" in the playground environment </li>
 <li> App is deployed on free tier with below limitations </li>
    <ul>
     <li> When there is no traffic over 30min, container is switched automatically to the sleeping mode. It means that first request takes longer as container is starting </li>
     <li> Database has limit up to 10k rows so there are just a few record there on which you can click around </li>
     <li> So because of above point also I disabled direct fetching from your file as it has more than 20k records </li>
     <li> Please don't run performance tests there :grin: </li>
    </ul>
 </ul>
 
 <h3> Tech stack </h3>
<ul>
  <li> As the requirement we should provide flexible and efficient way of querying. So becuase of this reason I decided to use GraphQL https://graphql.org/ </li>
  <li> It helps to query data in flexible way without tons of customized endpoints with complex query params structure </li>
  <li> I load file directly from yor S3 bucket </li>
  <li> As a data store I've used your favourite one - PostrgreSQL :) </li>
  <li> App bases on Spring Boot and the spring releated modules like Srping Data JPA.
  <li> Further details you will see in the code :relieved: </li>
 </ul>

<h3> Performance </h3>
<ul>
 <li> As data are rather historic and most likely are exposed for read the following applies: </li>
   <ul>
	 <li> Database is normalized according the Boyce–Codd normal form. That's why we have 3 tables. 
	 <li> For aggreation queries caching is added </li>
	 <li> I would create indexes for database as well (skipped scripts in this project). </li>
	 <li> IO and transforming operations are done asynchronously on the thread pool </li>
   </ul>
</ul>

<h3> Tests </h3>
<ul>
	<li> There are integeration tests included where you can check business scenarios. Please check also sample grapqhl queries in the tests resources. </li>
	<li> Unit tests if it's not obvious :yum: </li>
</ul>

<h3> Logging </h3>
	<li> Operation statuses and their attirbutes are logged </li>
	<li> sessions, requests-ids, user-agent etc. are not provided explictily in the logs for better clarity for this demo project </li>
</ul>
 <h3> Sample queries on live instance </h3>
  <ul>
   <li>Total Clicks for a given Datasource for a given Date range
	  
	   
   
Request:
 ```    
{
   totalClicksGroupedByDataSourceAndDate(queryParams:{
     name:"Google Ads"
     dateFrom: "2019-01-01"
     dateTo: "2021-06-30"
    })
}
```
Response:
```
{
 "data": {
   "totalClicksGroupedByDataSourceAndDate": 24
 }
}
```
</li>
 
<li>Click-Through Rate (CTR) per Datasource

Request:
```
{
  ctrGroupByDataSource(queryParams: {
    name: "Google Ads"
  })
}
```
  Response:
```
{
  "data": {
    "ctrGroupByDataSource": 0.0003535286579168324
  }
}
```
</li>

<li>Click-Through Rate (CTR) per Campaign 

Request:
```
{
  ctrGroupByCampaign(queryParams: {
  name: "Adventmarkt Touristik"
 })
}
```

Response
```
{
  "data": {
    "ctrGroupByCampaign": 0.0015666999462406882
  }
}
```
</li>
 
<li>Impressions over time (daily) 
 
Request
```
{
  filterByDates(queryParams:{
   dateFrom: "2019-01-02"
   dateTo:"2021-06-20"
  })
 {
  date
  impressions
		campaign
  }
}
```
Response
```
{
  "data": {
    "filterByDates": [
      {
        "date": "2020-06-12",
        "impressions": 100,
        "campaign": "GDN_Retargeting"
      },
      {
        "date": "2021-06-18",
        "impressions": 20,
        "campaign": "Adventmarkt Touristik"
      },
      {
        "date": "2019-12-03",
        "impressions": 3,
        "campaign": "Schutzbrief"
      },
      {
        "date": "2019-11-12",
        "impressions": 22425,
        "campaign": "Adventmarkt Touristik"
      },
      {
        "date": "2019-11-13",
        "impressions": 45452,
        "campaign": "Adventmarkt Touristik"
      },
      {
        "date": "2020-06-11",
        "impressions": 62313,
        "campaign": "Adventmarkt Touristik"
      }
    ]
  }
}
```
 </li>
 
 <li> Total impressions on specified dates
  
Request:

```
{
    totalImpressionsGroupedByDataSourceAndDate(queryParams:{
    name: "Google Ads"
    dateFrom: "2019-01-02"
    dateTo:"2021-06-20"
  })
  
}
```

Response:

```
{
  "data": {
    "totalImpressionsGroupedByDataSourceAndDate": 67877
  }
}
```
</li>
</ul>
