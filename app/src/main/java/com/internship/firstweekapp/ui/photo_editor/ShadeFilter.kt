package com.internship.firstweekapp.ui.photo_editor

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter

enum class ShadeFilter(val colorFilter: ColorMatrixColorFilter) {
    Normal(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
    ),
    BlackAndWhite(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    0.33f, 0.59f, 0.11f, 0f, 0f,
                    0.33f, 0.59f, 0.11f, 0f, 0f,
                    0.33f, 0.59f, 0.11f, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
    ),
    Invert(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    -1f, 0f, 0f, 0f, 255f,
                    0f, -1f, 0f, 0f, 255f,
                    0f, 0f, -1f, 0f, 255f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
    ),
    RGBtoBRG(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    0f, 0f, 1f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    1f, 0f, 0f, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
    ),
    Sepia(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    0.393f, 0.769f, 0.189f, 0f, 0f,
                    0.349f, 0.686f, 0.168f, 0f, 0f,
                    0.272f, 0.534f, 0.131f, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
    ),
    Polaroid(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1.438f, -0.122f, -0.016f, 0f, 0f,
                    -0.062f, 1.378f, -0.016f, 0f, 0f,
                    -0.062f, -0.122f, 1.483f, 0f, 0f,

                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
    ),
    WhiteToAlpha(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    -1f, -1f, -1f, 1f, 0f
                )
            )
        )
    ),
    Old(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    0.25f, 0.5f, 0.125f, 0f, 0f,
                    0.25f, 0.5f, 0.125f, 0f, 0f,
                    0.25f, 0.5f, 0.125f, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
    )

}