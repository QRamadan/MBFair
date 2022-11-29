/**
 */
package UMLFairness;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.BehavioredClassifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>indvidual Fairness</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link UMLFairness.indvidualFairness#getBase_BehavioredClassifier <em>Base Behaviored Classifier</em>}</li>
 *   <li>{@link UMLFairness.indvidualFairness#getExplanatoryData <em>Explanatory Data</em>}</li>
 *   <li>{@link UMLFairness.indvidualFairness#getSensitiveDecisions <em>Sensitive Decisions</em>}</li>
 *   <li>{@link UMLFairness.indvidualFairness#getThreshold <em>Threshold</em>}</li>
 *   <li>{@link UMLFairness.indvidualFairness#getMetric <em>Metric</em>}</li>
 * </ul>
 *
 * @see UMLFairness.UMLFairnessPackage#getindvidualFairness()
 * @model annotation="http://www.eclipse.org/uml2/2.0.0/UML originalName='indvidual-Fairness'"
 * @generated
 */
public interface indvidualFairness extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Behaviored Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Behaviored Classifier</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Behaviored Classifier</em>' reference.
	 * @see #setBase_BehavioredClassifier(BehavioredClassifier)
	 * @see UMLFairness.UMLFairnessPackage#getindvidualFairness_Base_BehavioredClassifier()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	BehavioredClassifier getBase_BehavioredClassifier();

	/**
	 * Sets the value of the '{@link UMLFairness.indvidualFairness#getBase_BehavioredClassifier <em>Base Behaviored Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Behaviored Classifier</em>' reference.
	 * @see #getBase_BehavioredClassifier()
	 * @generated
	 */
	void setBase_BehavioredClassifier(BehavioredClassifier value);

	/**
	 * Returns the value of the '<em><b>Explanatory Data</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Explanatory Data</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Explanatory Data</em>' attribute list.
	 * @see UMLFairness.UMLFairnessPackage#getindvidualFairness_ExplanatoryData()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getExplanatoryData();

	/**
	 * Returns the value of the '<em><b>Sensitive Decisions</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sensitive Decisions</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sensitive Decisions</em>' attribute list.
	 * @see UMLFairness.UMLFairnessPackage#getindvidualFairness_SensitiveDecisions()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getSensitiveDecisions();

	/**
	 * Returns the value of the '<em><b>Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Threshold</em>' attribute.
	 * @see #setThreshold(double)
	 * @see UMLFairness.UMLFairnessPackage#getindvidualFairness_Threshold()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getThreshold();

	/**
	 * Sets the value of the '{@link UMLFairness.indvidualFairness#getThreshold <em>Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Threshold</em>' attribute.
	 * @see #getThreshold()
	 * @generated
	 */
	void setThreshold(double value);

	/**
	 * Returns the value of the '<em><b>Metric</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metric</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metric</em>' attribute.
	 * @see #setMetric(String)
	 * @see UMLFairness.UMLFairnessPackage#getindvidualFairness_Metric()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getMetric();

	/**
	 * Sets the value of the '{@link UMLFairness.indvidualFairness#getMetric <em>Metric</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metric</em>' attribute.
	 * @see #getMetric()
	 * @generated
	 */
	void setMetric(String value);

} // indvidualFairness
