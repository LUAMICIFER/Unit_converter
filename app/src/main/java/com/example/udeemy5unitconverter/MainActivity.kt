package com.example.udeemy5unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.udeemy5unitconverter.ui.theme.Udeemy5unitconverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Udeemy5unitconverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Unitconverter()
                //                    Greeting("Android")
                }

            }
        }
    }
}
@Composable
fun Unitconverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iexpanded by remember { mutableStateOf(false) }
    var oexpanded by remember { mutableStateOf(false) }
    var mfactor = remember { mutableStateOf(0.01) }
    var omfactor = remember { mutableStateOf(1.0) }

    fun Convertunit(){
//        ?: elvis operator agar koi double value me change ho gaya to bhej dega wahi warna 0.0 bhej dega
        val inpu=inputValue.toDoubleOrNull() ?: 0.0
        val result = (inpu * mfactor.value*100.0/omfactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }

    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        all the ui elements will be placed below each other
//        Text(text = "Unit converter", Modifier.padding(100.dp))//this is also a way of giving space
        Text(text = "Unit converter")
        Spacer(modifier = Modifier.height(16.dp))//density pixel hr phone ka alag hota hai
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it //it takes the value of the text field and it returns a string named "it"
            Convertunit()
        },
            label = { Text(text = "Enter value") })//this is called a anonums function as it does not have a name and it cannot be called but still we can execute it
        //noting in the onvaluechange represents do nothing and can be used to execute actions if the value changes
//        val context = LocalContext.current // ye batayega kahan pr toast show karna hai
//        Button(onClick = { Toast.makeText(context, "Thanks for clicking  me", Toast.LENGTH_SHORT).show()}) {
////            Text(text = "click me!") // button ka text hai ye
//        }

        Spacer(modifier = Modifier.height(16.dp))
        Row {
//            all the ui element will be placed next to each other
            Box{
                Button(onClick = {iexpanded=true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "drop down")
                }
                DropdownMenu(expanded = iexpanded , onDismissRequest = { iexpanded = false })
                {
                    DropdownMenuItem(text = { Text(text = "centimeter") }, onClick = {
                        iexpanded = false
                        inputUnit = "centimeter"
                        mfactor.value = 0.01
                        Convertunit()
                    })
                    DropdownMenuItem(text = { Text(text = "meter") }, onClick = {
                        iexpanded = false
                        inputUnit = "meter"
                        mfactor.value = 1.0
                        Convertunit()
                    })
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {
                        iexpanded = false
                        inputUnit = "feet"
                        mfactor.value = 0.3048
                        Convertunit()
                    })
                    DropdownMenuItem(text = { Text(text = "milimeter") }, onClick = {
                        iexpanded = false
                        inputUnit = "milimeter"
                        mfactor.value = 0.001
                        Convertunit()
                    })

                }
            }
            Spacer(modifier = Modifier.padding(5.dp))

            Box{
                Button(onClick = { oexpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "drop down")
                }
                DropdownMenu(expanded = oexpanded , onDismissRequest = { /*TODO*/ })
                {
                    DropdownMenuItem(text = { Text(text = "centimeter") }, onClick = {
                        oexpanded = false
                        outputUnit = "centimeter"
                        omfactor.value = 0.01
                        Convertunit() })
                    DropdownMenuItem(text = { Text(text = "meter") }, onClick = {
                        oexpanded = false
                        outputUnit = "meter"
                        omfactor.value = 1.0
                        Convertunit()
                    })
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {
                        oexpanded = false
                        outputUnit = "feet"
                        omfactor.value = 0.3048
                        Convertunit()
                    })
                    DropdownMenuItem(text = { Text(text = "milimeter") }, onClick = {
                        oexpanded = false
                        outputUnit = "milimeter"
                        omfactor.value = 0.001
                        Convertunit()
                    })

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result : $outputValue $outputUnit")
    }
}
// lecture number 88 dekh lena
    
// remember function is used to create a persistence and  remembered state in jetpack compose it allows a composable to maintain its state across recomposistions even when the composable is recreated which means screen is refresehed every single second
//recomposition is the process of regenerating and updating the ui to reflect changes in the application state or user intraction in jet pack compose your ui is represented as tree of composables and recomposition is how


@Preview(showBackground=true)
@Composable
fun UnitconverterPreview(){
    Unitconverter()
}