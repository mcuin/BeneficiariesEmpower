package com.empower.beneficiaries

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.generateViewId
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

/**
 * Adapter for the list of beneficiary
 * @param clickBeneficiary function to be called when a beneficiary is clicked
 */
class BeneficiariesRecyclerViewAdapter(private val clickBeneficiary: (Beneficiary) -> Unit) : RecyclerView.Adapter<BeneficiariesRecyclerViewAdapter.ViewHolder>() {

    // Lateinit properties to be initialized later in the class for all views
    private lateinit var nameTextView: TextView
    private lateinit var benefitTypeTextView: TextView
    private lateinit var designationTextView: TextView
    private lateinit var values: List<Beneficiary>

    /**
     * Set the data for the adapter after it has been retrieved
     * @param data List of beneficiary to be displayed
     */
    fun setData(data: List<Beneficiary>) {
        println(data)
        values = data
        notifyDataSetChanged()
    }

    /**
     * Create a new ViewHolder for the RecyclerView
     * @param parent The parent ViewGroup
     * @param viewType The view type of the new View
     * @return A new ViewHolder that holds a ConstraintLayout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Create a new ConstraintLayout for the row
        val row = ConstraintLayout(parent.context)
        row.layoutParams = RecyclerView.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        return ViewHolder(row)
    }

    /**
     * Called by RecyclerView to display the data at the specified position
     * @param holder The ViewHolder that should be updated
     * @param position The position of the item within the adapter's data set
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        nameTextView.text = String.format(holder.itemView.context.getString(R.string.beneficiary_name), item.firstName, item.lastName)
        benefitTypeTextView.text = item.beneType
        designationTextView.text = if (item.designationCode == "P") {
            holder.itemView.context.getString(R.string.primary)
        } else {
            holder.itemView.context.getString(R.string.contingent)
        }

        holder.itemView.setOnClickListener {
            clickBeneficiary(item)
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter
     */
    override fun getItemCount(): Int = values.count()

    /**
     * ViewHolder for the RecyclerView
     * @param row The ConstraintLayout representing the row
     */
    inner class ViewHolder(row: ConstraintLayout) : RecyclerView.ViewHolder(row) {

        /**
         * Initialize the views in the row
         */
        init {
            // Create a new ConstraintLayout for the beneficiary row
            val beneficiaryRow = ConstraintLayout(row.context)
            beneficiaryRow.layoutParams = RecyclerView.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )

            row.addView(beneficiaryRow)

            // Initialize the views in the beneficiary row
            nameTextView = TextView(beneficiaryRow.context).apply {
                id = generateViewId()
                layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT)
            }
            benefitTypeTextView = TextView(beneficiaryRow.context).apply {
                id = generateViewId()
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            }
            designationTextView = TextView(beneficiaryRow.context).apply {
                id = generateViewId()
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            }
            beneficiaryRow.addView(nameTextView)
            beneficiaryRow.addView(benefitTypeTextView)
            beneficiaryRow.addView(designationTextView)

            // Set and apply the constraints for the views in the beneficiary row
            val beneficiaryConstraintSet = ConstraintSet()
            beneficiaryConstraintSet.clone(beneficiaryRow)
            beneficiaryConstraintSet.connect(
                nameTextView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                beneficiaryRow.context.resources.getDimension(R.dimen.standard_margin).toInt()
            )
            beneficiaryConstraintSet.connect(
                nameTextView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                beneficiaryRow.context.resources.getDimension(R.dimen.standard_margin).toInt()
            )
            beneficiaryConstraintSet.connect(
                benefitTypeTextView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                beneficiaryRow.context.resources.getDimension(R.dimen.standard_margin).toInt()
            )
            beneficiaryConstraintSet.connect(
                benefitTypeTextView.id,
                ConstraintSet.TOP,
                nameTextView.id,
                ConstraintSet.BOTTOM,
                beneficiaryRow.context.resources.getDimension(R.dimen.xs_marging).toInt()
            )
            beneficiaryConstraintSet.connect(
                benefitTypeTextView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM,
                beneficiaryRow.context.resources.getDimension(R.dimen.standard_margin).toInt()
            )
            beneficiaryConstraintSet.connect(
                designationTextView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                beneficiaryRow.context.resources.getDimension(R.dimen.standard_margin).toInt()
            )
            beneficiaryConstraintSet.connect(
                designationTextView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                beneficiaryRow.context.resources.getDimension(R.dimen.standard_margin).toInt()
            )
            beneficiaryConstraintSet.connect(
                designationTextView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM,
                beneficiaryRow.context.resources.getDimension(R.dimen.standard_margin).toInt()
            )
            beneficiaryConstraintSet.connect(
                nameTextView.id,
                ConstraintSet.END,
                designationTextView.id,
                ConstraintSet.START,
                beneficiaryRow.context.resources.getDimension(R.dimen.xs_marging).toInt()
            )
            beneficiaryConstraintSet.connect(
                designationTextView.id,
                ConstraintSet.START,
                nameTextView.id,
                ConstraintSet.END,
                beneficiaryRow.context.resources.getDimension(R.dimen.xs_marging).toInt()
            )
            beneficiaryConstraintSet.applyTo(beneficiaryRow)

        }
    }
}