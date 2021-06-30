# simple-data-adverity-warehouse

Hey guys

Thank you for interesting challenge.

<h2>Demo:</h2>

https://www.youtube.com/watch?v=ciuQhwM48z8

You can play with the service by your own (check links in the email from me).
<h3> Demo notes </h3>
<ul>
 <li> App is deployed on free tier with below limitations </li>
    <ul>
     <li> When thers is no traffic over 30min, container is switched automatically to the sleeping mode. It means that first request takes longer as container is starting </li>
     <li> Database has limit up to 10k rows so there are just a few record there on which you can click around </li>
     <li> So because of above point also I disabled direct fetching from your file as it has more than 20k records </li>
     <li> Please don't run performance tests there :grin: </li>
    </ul>
 </ul>

<h3> Sample queries on live instance</h3>
```
totalClicksGroupedByDataSourceAndDate(queryParams:{
  name:"Google Ads"
  dateFrom: "2019-01-01"
  dateTo: "2021-06-30"
})
```

<h3> Tech stack </h3>
<ul>
  <li> As the requirement we should provide flexible and efficient way of querying. So becuase of this reason I decided to use GraphQL https://graphql.org/ </li>
  <li> It helps to query data in flexible way without tons of customized endpoints with complex query params structure </li>
  <li> I load file directly from yor S3 bucket </li>
  <li> As a data store I've used your favourite one - PostrgreSQL :) </li>
  <li> Further details you will see in the code </li>
 </ul>

<h3> Performance </h3>
<ul>
 <li> Database is normalized according the 
 <li> For aggreation queries caching is added </li>
 <li> As a data 
 <li> IO operations are done asynchronously </li>
</ul>
