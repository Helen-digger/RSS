package util

import jp.wasabeef.glide.transformations.BitmapTransformation
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation

enum class FilterTypes {

    None {
        override fun getTransformationByName(): BitmapTransformation? {
            return null
        }
    },
    Blur {
        override fun getTransformationByName(): BitmapTransformation {
            return BlurTransformation()
        }
    },
    Sepia{
        override fun getTransformationByName(): BitmapTransformation {
            return SepiaFilterTransformation()
        }
    },
    Contrast{
        override fun getTransformationByName(): BitmapTransformation {
            return ContrastFilterTransformation()
        }
    },
    Invert{
        override fun getTransformationByName(): BitmapTransformation {
            return InvertFilterTransformation()
        }
    };

    abstract fun getTransformationByName() : BitmapTransformation?

    companion object {
        fun getByName(name : String) : FilterTypes {
            return FilterTypes.valueOf(name)
        }
    }


}