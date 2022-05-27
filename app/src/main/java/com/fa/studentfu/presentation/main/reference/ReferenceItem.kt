package com.fa.studentfu.presentation.main.reference

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fa.studentfu.R
import com.fa.studentfu.data.models.ReferenceModel
import com.fa.studentfu.databinding.ReferenceListElementBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ReferenceItem(private val reference : ReferenceModel, private val context: Context)
    : AbstractBindingItem<ReferenceListElementBinding>() {

    override val type: Int
        get() = R.id.reference_recycler

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ReferenceListElementBinding {
        return ReferenceListElementBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ReferenceListElementBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.referenceItemLocation.text = reference.location
        binding.referenceItemStatus.text = status[reference.status]
        binding.referenceItemEducationForm.text = form[reference.eduForm]
        binding.referenceOrganization.text = reference.organizationFor
        binding.referenceItemReceivingMethod.text = method[reference.pickupMethod]
    }

    companion object{
        private val status = mapOf(
            "Rd" to "Получено в работу",
            "IP" to "В процессе",
            "Fd" to "Изготовлена",
            "RR" to "Готова к получению",
        )

        private val form = mapOf(
            "F" to "Очная",
            "P" to "Очно-заочная",
            "D" to "Заочная",
        )

        private val method = mapOf(
            "S" to "Самовывоз",
            "D" to "Почта России",
        )
    }
}