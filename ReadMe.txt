Order Book Simulation (Madhur Bhattad)


A few important classes used in the assignment: Exchange(It contains the main method), Market, Comms, OfferBook, BidBook, OrderConfirmations 
We also use a PriceComparator and an anti price comparator for the purpose of using tree maps with generalized sorting
Tree maps being always sorted irrespecive of an addiditon or deletion of an element is quite advantageous for the execution of order books
Our OfferBook and BidBook are tree maps with both key and value as a price object
Price class contains a double price instance value and a list of all the Resting Orders with of that price
Another important use of data structure in this assignment was realizing the advantages of having LinkedList of resting orders rather than an array list
This is due to multiple use of addition and deletion of an element  

Due to the changes in design, the exact test case that was shown does not work in my solution. But I have made a few changes that should be reflected by my junit test cases


