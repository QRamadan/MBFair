/**
 */
package UMLFairness;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.StructuredClassifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>critical</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link UMLFairness.critical#getProtectedData <em>Protected Data</em>}</li>
 *   <li>{@link UMLFairness.critical#getBase_StructuredClassifier <em>Base Structured Classifier</em>}</li>
 * </ul>
 *
 * @see UMLFairness.UMLFairnessPackage#getcritical()
 * @model
 * @generated
 */
public interface critical extends EObject {
	/**
	 * Returns the value of the '<em><b>Protected Data</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Protected Data</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Protected Data</em>' attribute list.
	 * @see UMLFairness.UMLFairnessPackage#getcritical_ProtectedData()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getProtectedData();

	/**
	 * Returns the value of the '<em><b>Base Structured Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Structured Classifier</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Structured Classifier</em>' reference.
	 * @see #setBase_StructuredClassifier(StructuredClassifier)
	 * @see UMLFairness.UMLFairnessPackage#getcritical_Base_StructuredClassifier()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	StructuredClassifier getBase_StructuredClassifier();

	/**
	 * Sets the value of the '{@link UMLFairness.critical#getBase_StructuredClassifier <em>Base Structured Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Structured Classifier</em>' reference.
	 * @see #getBase_StructuredClassifier()
	 * @generated
	 */
	void setBase_StructuredClassifier(StructuredClassifier value);

} // critical
