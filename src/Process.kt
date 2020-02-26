import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Process(val name:String){

    var max:Map<Resource,Int> = HashMap()
    var alloc:Map<Resource,Int> = HashMap()

    // need is calculated as max - alloc
    var need:Map<Resource,Int> = HashMap()

    companion object{
        fun readProcess(resources:ArrayList<Resource>):Process{
            println("Enter the process name")

            // reading the name of the process and create a process
            // with that name
            val process = Process(scanner.nextLine())

            // read the alloc and max
            for(resource in resources){
                println("Enter the alloc number of resources of type ${resource.name}")
                val alloc = scanner.nextInt()
                println("Enter the max needed number of resources of type ${resource.name}")
                val max = scanner.nextInt()

                // adding this data to the maps
                process.alloc += resource to alloc
                process.max += resource to max
                process.need += resource to (max-alloc)
            }
            // ignoring the endline '\n'
            scanner.nextLine()
            return process
        }
    }
}