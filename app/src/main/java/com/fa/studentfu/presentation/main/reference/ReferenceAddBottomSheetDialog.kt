package com.fa.studentfu.presentation.main.reference

import android.content.Context
import android.widget.ArrayAdapter
import androidx.core.view.allViews
import com.fa.studentfu.core.ui.BaseBottomSheetDialogFragment
import com.fa.studentfu.core.ui.EditTextErrorWatcher
import com.fa.studentfu.databinding.ReferenceAddFragmentBinding
import com.google.android.material.textfield.TextInputLayout

class ReferenceAddBottomSheetDialog :
    BaseBottomSheetDialogFragment<ReferenceAddFragmentBinding>(ReferenceAddFragmentBinding::inflate) {

    lateinit var viewModel: ReferenceViewModel
    lateinit var formAdapter : ArrayAdapter<String>
    lateinit var receiveMethod : ArrayAdapter<String>
    lateinit var territory: ArrayAdapter<String>
    lateinit var department : ArrayAdapter<String>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = (requireParentFragment() as ReferenceFragment).viewModel
        isCancelable = false
        formAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, listOf(
            "Очная", "Очно-заочная", "Заочная"
        ))
        receiveMethod = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listOf(
            "Самовывоз", "Почта России"
        ))
        territory = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listOf(
            "Ленинградский пр-т, д.49, комната 150,", "ул. Кибальчича, д.1, к.3",
            "Малый Златоустинский переулок, д.7, стр.1, к.110", "4-й Вешняковский проезд, д.4, к.112",
            "Верхняя Масловка, д. 15, к. 149", "ул. Щербаковская, д.38, к. 302", "ул. Олеко Дундича, д.23, к.249"
        ))
        department = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listOf(
            "Факультет международных экономических отношений",
            "Финансовый факультет", "Факультет экономики и бизнеса",
            "Факультет \"Высшая школа управления\"", "Факультет информационных технологий и анализа больших данных",
            "Факультет налогов, аудита и бизнес-анализа", "Факультет социальных наук и массовых коммуникаций",
            "Юридический факультет", "Подготовительный факультет", "Институт аспирантуры",
            "Институт заочного образования", "Институт онлайн-образования", "Институт сокращённых программ"
        ))
    }

    override fun initializeViews() {
        super.initializeViews()
        binding.referenceDialogClose.setOnClickListener{
            dismiss()
        }
        binding.referenceDialogFormEdit.setAdapter(formAdapter)
        binding.referenceDialogMethodEdit.setAdapter(receiveMethod)
        binding.referenceDialogAddressEdit.setAdapter(territory)
        binding.referenceDialogDepartmentEdit.setAdapter(department)

        binding.groupInputs.referencedIds.forEach {
            view?.findViewById<TextInputLayout>(it)?.editText?.addTextChangedListener(
                EditTextErrorWatcher(
                    (view?.findViewById(it) as TextInputLayout)
                )
            )
        }

        binding.referenceDialogSave.setOnClickListener {
            var areEmpty = false
            binding.groupInputs.referencedIds.forEach{
                val editText = view?.findViewById<TextInputLayout>(it)?.editText
                if (editText?.text?.isEmpty() == true){
                    areEmpty = true
                    view?.findViewById<TextInputLayout>(it)?.error = "Заполните это поле"
                }
            }
            if (!areEmpty) {
                dismiss()
            }
        }
    }

    override fun setupListeners() {
        super.setupListeners()
    }

    override fun observe() {
        super.observe()
    }
}