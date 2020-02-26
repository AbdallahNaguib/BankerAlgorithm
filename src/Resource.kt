
class Resource(val name:String,var avail:Int){
    companion object{
        fun readRes():Resource{
            println("Enter the resource name")
            val name = scanner.nextLine()
            println("Enter the number of available resources of $name")
            val avail = scanner.nextInt()
            // ignoring the endline '\n'
            scanner.nextLine()
            return Resource(name,avail)
        }
    }
}