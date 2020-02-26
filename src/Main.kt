import java.util.*
import kotlin.collections.ArrayList

var scanner=Scanner(System.`in`)

fun main(){
    val resources = ArrayList<Resource>()
    println("Enter the number of resources")
    val resourcesCnt = scanner.nextInt()

    // ignoring the endline '\n'
    scanner.nextLine()
    for(i in 1..resourcesCnt){
        resources.add(Resource.readRes())
    }

    val processes = ArrayList<Process>()
    println("Enter the number of processes")
    val processesCnt = scanner.nextInt()

    // ignoring the endline '\n'
    scanner.nextLine()
    for(i in 1..processesCnt){
        processes.add(Process.readProcess(resources))
    }
    val orderOfExecution = bankerAlgo(processes)

    if (orderOfExecution == null) {
        println("Deadlock occurs")
    }else{
        print("a possible way of execution : ")
        for (process in orderOfExecution){
            print("${process.name} ")
        }
    }
    println()
    for(res in resources){
        println("${res.name} = ${res.avail}")
    }
}

// this method returns an arraylist of the processes in the order
// they will be executed and returns null if no order exists
fun bankerAlgo(processes:ArrayList<Process>):ArrayList<Process>?{
    val orderOfProcesses = ArrayList<Process>()

    // keep iterating till there's no process to be executed
    while(!processes.isEmpty()){
        val process = getGoodProcess(processes)

        // if no process could be executed then it's a deadlock
        if (process == null) {
            return null
        }

        // add this process to the order , delete from the processes
        // that will run and give its resources back
        orderOfProcesses.add(process)
        processes.remove(process)
        process.alloc.forEach {resource,allocatedCnt->
            resource.avail += allocatedCnt
        }
    }
    return orderOfProcesses
}

// this method returns any process that could be executed and
// null if no such process exists
fun getGoodProcess(processes: ArrayList<Process>):Process?{
    for(process in processes){
        var ok = true
        process.need.forEach { resource, req ->
            if(req > resource.avail) ok = false
        }
        if(ok){
            return process
        }
    }
    return null
}

