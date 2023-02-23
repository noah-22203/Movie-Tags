# Movie-Tag
My code starts by creating an ArrayList of Tag objects. Tag is a separate class that I created so that I could store
an integer value as well as a string value into the same object. This lets me keep track of each tag, as well as
how many times they occur. Then, my function calls ReadFile, which takes each line of the csv file and compares 
it to the ArrayList of Tags. If it already exists, the count is incremented, otherwise it adds it to the arraylist.
Running time is O(n^2),  because each line of input requires a linear search on the array tagEntry, which may 
contain up to “n” entries. 

Next, my code calls SortFile(), which implements mergesort that we learned in class. the only change that I made to 
the mergesort algorithm other than making it work with my array type was making it so that values of the same count
are sorted alphabetically. the runtime of my mergesort function is O(n log(n)).

Finally, my function prints out a few lines and calls UserIn(). this function is for taking in user input and searching
through the arraylist, for which I used linear search when searching for both tags and counts. I did this because
using linear search for this function still returns the users answer almost instantly, so I decided that it was not 
worth my time to write a separate search function. each time the user enters a valid search entry there is a runtime
of O(n). this is the final function that is called in main, and it continues running until the user enters "EXIT", 
which terminates the entire program.