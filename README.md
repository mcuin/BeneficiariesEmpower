# BeneficiariesEmpower

I would have preferred to have implemented the viewmodel and repository with dependency injection rather than passing the asset 
manager along due to the need for application context in the repository to be able to get to the assets folder. I also would have 
liked to make it a little more colorful and themed, but felt a bit too much time pressure towards the end. 

The reason I ended up using the Gson library is due to time constraints. I fiddled with JSONObject/Array for about 2 hours before
implemented Gson. I was having issues with the JSONArray not being iterative for each object. The plan was to iterate over the array, 
and transform each object into a JSONObject I could work with. In the end though my preference is with Gson and Data Classes
to work with data through. 

This is the second I have ever dealt with building UIs directly through code. It does get a bit faster once I had my head wrapped around it, 
but I mainly struggled on how to handle it with a Recyclerview viewholder. Overall I can see why Compose was spawned from this thought 
of UI development. 