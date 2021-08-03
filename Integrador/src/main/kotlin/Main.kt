import java.util.*

fun main(args: Array<String>) {

    val parking = Parking(mutableSetOf())

    val car = Vehicle("AA111AA", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_001")
    val moto = Vehicle("B222BBB", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus = Vehicle("CC333CC", VehicleType.MINIBUS, Calendar.getInstance())
    val bus = Vehicle("DD444DD", VehicleType.BUS, Calendar.getInstance(), "DISCOUNT_CARD_002")
    val car2 = Vehicle("AA111AA", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_003")
    val car3 = Vehicle("EE555EE", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_004")
    val moto2 = Vehicle("FF66FFF", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val bus2 = Vehicle("GG77GGG", VehicleType.BUS, Calendar.getInstance())
    val bus3 = Vehicle("HH88HHH", VehicleType.MINIBUS, Calendar.getInstance())
    val car4 = Vehicle("II99III", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_005")
    val moto3 = Vehicle("JJ111JJJ", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val minibus2 = Vehicle("KK222KK", VehicleType.MINIBUS, Calendar.getInstance())
    val bus4 = Vehicle("LL333LL", VehicleType.BUS, Calendar.getInstance(), "DISCOUNT_CARD_006")
    val car5 = Vehicle("MM444MM", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_007")
    val car6 = Vehicle("NN555NN", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_008")
    val moto4 = Vehicle("OO66OO", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val bus5 = Vehicle("PP77PPP", VehicleType.BUS, Calendar.getInstance())
    val bus6 = Vehicle("QQ88QQ", VehicleType.MINIBUS, Calendar.getInstance())
    val minibus3 = Vehicle("RR99RR", VehicleType.MINIBUS, Calendar.getInstance())
    val bus7 = Vehicle("SS111SS", VehicleType.BUS, Calendar.getInstance(), "DISCOUNT_CARD_009")
    val minibus4 = Vehicle("TT22TT", VehicleType.MINIBUS, Calendar.getInstance())
    val bus8 = Vehicle("UU33UUU", VehicleType.BUS, Calendar.getInstance())
    val vehiclesList = mutableListOf(car, moto, minibus, bus, car2, car3, moto2, bus2, bus3, car4, moto3, minibus2, bus4, car5, car6, moto4, bus5, bus6, minibus3, bus7, minibus4, bus8)

    for (v in vehiclesList) {
        if(parking.addVehicle(v)) println("Welcome to AlkeParking!")
        else println("Sorry, the check-in failed")
    }
    /*función que recibe los datos del vehículo y valida el check-out y posteriormente la función
    que calcula el cobro.*/

}

data class Parkable(var vehicle : Vehicle) {

    companion object {
        fun checkIn(vehicle: Vehicle, vehicles: MutableSet<Vehicle>): Boolean {
            if(VehicleType.isValidVehicle(vehicle)) {
                for(v in vehicles){
                    if(v.equals(vehicle)){
                        return false
                    }
                }
                return true
            }
            return false
        }
    }

    fun checkOutVehicle(plate: String, onSuccess: (Int), onError: Unit, vehicles: MutableSet<Vehicle>) {

        for (v in vehicles) {
            if(plate == v.plate) {
                var vehicleFound : Vehicle = v
                vehicles.remove(v)
                return onSuccess(60)
            }
        }
        return onError()
    }

    fun onSuccess(rate: Int){

    }

    fun onError() {

    }

}

data class Parking(val vehicles : MutableSet<Vehicle>) {

    fun addVehicle(vehicle: Vehicle): Boolean {
        if(this.vehicles.size < 20 && Parkable.checkIn(vehicle, vehicles)) {
            this.vehicles.add(vehicle)
            return true
        }
        return false
    }
}

data class Vehicle(val plate : String, val type : VehicleType, val checkInTime : Calendar, val discountCard : String? = null){

    companion object{
        const val MINUTES_IN_MILISECONDS = 60000
    }

    override fun equals(other: Any?): Boolean {
        if(other is Vehicle) {
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int{
        return this.plate.hashCode()
    }

    val parkedTime: Long
        get() = (Calendar.getInstance().timeInMillis - checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS

}

enum class VehicleType(val price: Int) {
    CAR(20),
    MOTORCYCLE(15),
    MINIBUS(25),
    BUS(30);

    companion object{
        fun isValidVehicle(vehicle: Vehicle) : Boolean {
            return when(vehicle.type) {
                BUS, MOTORCYCLE, CAR, MINIBUS ->  true
                else ->  false
            }

        }
    }

}

