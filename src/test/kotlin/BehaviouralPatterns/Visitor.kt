package BehaviouralPatterns

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

interface ReportElement{
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(val costPerYear: Long): ReportElement{
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class TimeAndMaterialsContract(val costPerhour: Long, val hours: Long): ReportElement{
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class SupportContract(val costPerMonth: Long): ReportElement{
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

interface ReportVisitor<out R> {
    fun visit(contract: FixedPriceContract ): R
    fun visit(contract: TimeAndMaterialsContract): R
    fun visit(contract: SupportContract): R
}

class MonthyCostReportVisitor: ReportVisitor<Long>{

    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear / 12

    override fun visit(contract: TimeAndMaterialsContract): Long = contract.costPerhour * contract.hours

    override fun visit(contract: SupportContract): Long = contract.costPerMonth
}

class YearlyCostReportVisitor: ReportVisitor<Long>{
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear

    override fun visit(contract: TimeAndMaterialsContract): Long = contract.costPerhour * contract.hours

    override fun visit(contract: SupportContract): Long = contract.costPerMonth * 12

}

class VisitorTest{
    @Test
    fun testVisitor(){
        val projectAlpha = FixedPriceContract(10000)
        val projectBeta = SupportContract(500)
        val projectGamma = TimeAndMaterialsContract(150, 10)
        val projectKappa = TimeAndMaterialsContract(50, 50)

        val project = arrayListOf(projectAlpha, projectBeta, projectGamma, projectKappa)

        val monthlyCostVisitor = MonthyCostReportVisitor()
        val monthlyCost = project.map { it.accept(monthlyCostVisitor) }.sum()

        println("Monthly cost: $monthlyCost")
        Assertions.assertThat(monthlyCost).isEqualTo(5333)

        val yearluCostVisitor = YearlyCostReportVisitor()
        val yearlyCost = project.map { it.accept(yearluCostVisitor) }.sum()
        println("Yearly cosnt: $yearlyCost")
        Assertions.assertThat(yearlyCost).isEqualTo(20_000)
    }
}