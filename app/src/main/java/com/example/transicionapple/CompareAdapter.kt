import android.app.Dialog
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.transicionapple.R
import com.example.transicionapple.models.iPhoneModel

class CompareAdapter(private val models: List<iPhoneModel>) : RecyclerView.Adapter<CompareAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Columnas para el primer modelo
        val iphoneImageLeft: ImageView = view.findViewById(R.id.iphoneImageLeft)
        val modelVersionLeft: TextView = view.findViewById(R.id.modelVersionLeft)
        val screenSizeLeft: TextView = view.findViewById(R.id.screenSizeLeft)
        val storageLeft: TextView = view.findViewById(R.id.storageLeft)
        val batteryLeft: TextView = view.findViewById(R.id.batteryLeft)
        val frontCameraLeft: TextView = view.findViewById(R.id.frontCameraLeft)
        val mainCameraLeft: TextView = view.findViewById(R.id.mainCameraLeft)
        val zoomMaxLeft: TextView = view.findViewById(R.id.zoomMaxLeft)
        val processorLeft: TextView = view.findViewById(R.id.processorLeft)
        val ramLeft: TextView = view.findViewById(R.id.ramLeft)
        val aspectRatioLeft: TextView = view.findViewById(R.id.aspectRatioLeft)
        val resolutionLeft: TextView = view.findViewById(R.id.resolutionLeft)
        val fingerprintSensorLeft: TextView = view.findViewById(R.id.fingerprintSensorLeft)
        val proximitySensorLeft: TextView = view.findViewById(R.id.proximitySensorLeft)
        val gyroscopeSensorLeft: TextView = view.findViewById(R.id.gyroscopeSensorLeft)
        val compassSensorLeft: TextView = view.findViewById(R.id.compassSensorLeft)
        val showPhoneSensorsButtonLeft: Button = view.findViewById(R.id.showPhoneSensorsButtonLeft)

        // Columnas para el segundo modelo
        val iphoneImageRight: ImageView = view.findViewById(R.id.iphoneImageRight)
        val modelVersionRight: TextView = view.findViewById(R.id.modelVersionRight)
        val screenSizeRight: TextView = view.findViewById(R.id.screenSizeRight)
        val storageRight: TextView = view.findViewById(R.id.storageRight)
        val batteryRight: TextView = view.findViewById(R.id.batteryRight)
        val frontCameraRight: TextView = view.findViewById(R.id.frontCameraRight)
        val mainCameraRight: TextView = view.findViewById(R.id.mainCameraRight)
        val zoomMaxRight: TextView = view.findViewById(R.id.zoomMaxRight)
        val processorRight: TextView = view.findViewById(R.id.processorRight)
        val ramRight: TextView = view.findViewById(R.id.ramRight)
        val aspectRatioRight: TextView = view.findViewById(R.id.aspectRatioRight)
        val resolutionRight: TextView = view.findViewById(R.id.resolutionRight)
        val fingerprintSensorRight: TextView = view.findViewById(R.id.fingerprintSensorRight)
        val proximitySensorRight: TextView = view.findViewById(R.id.proximitySensorRight)
        val gyroscopeSensorRight: TextView = view.findViewById(R.id.gyroscopeSensorRight)
        val compassSensorRight: TextView = view.findViewById(R.id.compassSensorRight)
        val showPhoneSensorsButtonRight: Button = view.findViewById(R.id.showPhoneSensorsButtonRight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_compare, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelLeft = models[0] // Primer modelo
        val modelRight = models[1] // Segundo modelo

        // Asignar los datos del primer modelo a la columna izquierda
        holder.modelVersionLeft.text = modelLeft.modelVersion
        holder.screenSizeLeft.text = "Tamaño de pantalla: ${modelLeft.screenSize}"
        holder.storageLeft.text = "${modelLeft.storage}"
        holder.batteryLeft.text = "Batería: ${modelLeft.battery} mAh"
        holder.frontCameraLeft.text = "Cámara frontal: ${modelLeft.frontCamera} Mpx"
        holder.mainCameraLeft.text = "Cámara principal: ${modelLeft.mainCamera} Mpx"
        holder.zoomMaxLeft.text = "Zoom: Hasta ${modelLeft.zoomMax}x"
        holder.processorLeft.text = "Procesador: ${modelLeft.processor}"
        holder.ramLeft.text = "RAM: ${modelLeft.ram}"
        holder.aspectRatioLeft.text = "Aspecto Ratio: ${modelLeft.aspectRatio}"
        holder.resolutionLeft.text = "Resolución: ${modelLeft.resolution}"
        holder.fingerprintSensorLeft.text = "Sensor huella dactilar: ${modelLeft.fingerprintSensor}"
        holder.proximitySensorLeft.text = "Sensor de proximidad: ${modelLeft.proximitySensor}"
        holder.gyroscopeSensorLeft.text = "Sensor giroscópico: ${modelLeft.gyroscopeSensor}"
        holder.compassSensorLeft.text = "Sensor compás (brújula): ${modelLeft.compassSensor}"

        // Cargar imagen del primer modelo
        val context = holder.itemView.context
        val resourceIdLeft = context.resources.getIdentifier(modelLeft.imageName, "drawable", context.packageName)
        holder.iphoneImageLeft.setImageResource(resourceIdLeft)

        // Configurar botón de sensores del teléfono izquierdo
        holder.showPhoneSensorsButtonLeft.setOnClickListener {
            showPhoneSensorsDialog(holder.itemView.context)
        }

        // Asignar los datos del segundo modelo a la columna derecha
        holder.modelVersionRight.text = modelRight.modelVersion
        holder.screenSizeRight.text = "Tamaño de pantalla: ${modelRight.screenSize}"
        holder.storageRight.text = "${modelRight.storage}"
        holder.batteryRight.text = "Batería: ${modelRight.battery} mAh"
        holder.frontCameraRight.text = "Cámara frontal: ${modelRight.frontCamera} Mpx"
        holder.mainCameraRight.text = "Cámara principal: ${modelRight.mainCamera} Mpx"
        holder.zoomMaxRight.text = "Zoom: Hasta ${modelRight.zoomMax}x"
        holder.processorRight.text = "Procesador: ${modelRight.processor}"
        holder.ramRight.text = "RAM: ${modelRight.ram}"
        holder.aspectRatioRight.text = "Aspecto Ratio: ${modelRight.aspectRatio}"
        holder.resolutionRight.text = "Resolución: ${modelRight.resolution}"
        holder.fingerprintSensorRight.text = "Sensor huella dactilar: ${modelRight.fingerprintSensor}"
        holder.proximitySensorRight.text = "Sensor de proximidad: ${modelRight.proximitySensor}"
        holder.gyroscopeSensorRight.text = "Sensor giroscópico: ${modelRight.gyroscopeSensor}"
        holder.compassSensorRight.text = "Sensor compás (brújula): ${modelRight.compassSensor}"

        // Cargar imagen del segundo modelo
        val resourceIdRight = context.resources.getIdentifier(modelRight.imageName, "drawable", context.packageName)
        holder.iphoneImageRight.setImageResource(resourceIdRight)

        // Configurar botón de sensores del teléfono derecho
        holder.showPhoneSensorsButtonRight.setOnClickListener {
            showPhoneSensorsDialog(holder.itemView.context)
        }
    }

    private fun showPhoneSensorsDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_sensors)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        // Obtener el SensorManager y la lista de sensores disponibles
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        val sensorsTextView: TextView = dialog.findViewById(R.id.sensorsTextView)
        sensorsTextView.text = sensorList.joinToString("\n") { it.name }

        val closeButton: Button = dialog.findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun getItemCount() = 1 // Un solo ítem para comparar dos modelos
}