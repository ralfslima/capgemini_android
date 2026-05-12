// ******* PRECISA ADICIONAR PERMISSÃO NO ANDROIDMANIFEST
// <uses-permission android:name="android.permission.CAMERA"/>

// ******* ADICIONAR NO GRADLE (APP)
// val cameraxVersion = "1.3.3"
// implementation("androidx.camera:camera-core:$cameraxVersion")
// implementation("androidx.camera:camera-camera2:$cameraxVersion")
// implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
// implementation("androidx.camera:camera-view:$cameraxVersion")

package com.example.recursossmartphone.telas

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun Camera() {

    val contexto = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Permissão
    var permissaoConcedida by remember {
        mutableStateOf(false)
    }

    // Solicitar permissão
    val permissao = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { permitido ->

        permissaoConcedida = permitido

    }

    // Solicitar automaticamente
    LaunchedEffect(Unit) {

        permissao.launch(
            Manifest.permission.CAMERA
        )

    }

    // Exibir câmera
    if(permissaoConcedida) {

        AndroidView(

            factory = { ctx ->

                val previewView = PreviewView(ctx)

                val cameraProviderFuture =
                    ProcessCameraProvider.getInstance(ctx)

                cameraProviderFuture.addListener({

                    val cameraProvider =
                        cameraProviderFuture.get()

                    // Preview
                    val preview = Preview.Builder().build()

                    // Conectar preview na tela
                    preview.setSurfaceProvider(
                        previewView.surfaceProvider
                    )

                    // Câmera traseira
                    val cameraSelector =
                        CameraSelector.DEFAULT_BACK_CAMERA

                    try {

                        cameraProvider.unbindAll()

                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview
                        )

                    } catch(e: Exception) {
                        e.printStackTrace()
                    }

                }, ContextCompat.getMainExecutor(ctx))

                previewView

            }

        )

    }

}