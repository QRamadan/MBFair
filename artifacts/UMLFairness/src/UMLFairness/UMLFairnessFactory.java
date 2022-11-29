/**
 */
package UMLFairness;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see UMLFairness.UMLFairnessPackage
 * @generated
 */
public interface UMLFairnessFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UMLFairnessFactory eINSTANCE = UMLFairness.impl.UMLFairnessFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>indvidual Fairness</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>indvidual Fairness</em>'.
	 * @generated
	 */
	indvidualFairness createindvidualFairness();

	/**
	 * Returns a new object of class '<em>critical</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>critical</em>'.
	 * @generated
	 */
	critical createcritical();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UMLFairnessPackage getUMLFairnessPackage();

} //UMLFairnessFactory
