/**
 */
package UMLFairness;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see UMLFairness.UMLFairnessFactory
 * @model kind="package"
 * @generated
 */
public interface UMLFairnessPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "UMLFairness";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://koblenz.de/umlfair/2020/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UMLFairness";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UMLFairnessPackage eINSTANCE = UMLFairness.impl.UMLFairnessPackageImpl.init();

	/**
	 * The meta object id for the '{@link UMLFairness.impl.indvidualFairnessImpl <em>indvidual Fairness</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see UMLFairness.impl.indvidualFairnessImpl
	 * @see UMLFairness.impl.UMLFairnessPackageImpl#getindvidualFairness()
	 * @generated
	 */
	int INDVIDUAL_FAIRNESS = 0;

	/**
	 * The feature id for the '<em><b>Base Behaviored Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER = 0;

	/**
	 * The feature id for the '<em><b>Explanatory Data</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDVIDUAL_FAIRNESS__EXPLANATORY_DATA = 1;

	/**
	 * The feature id for the '<em><b>Sensitive Decisions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDVIDUAL_FAIRNESS__SENSITIVE_DECISIONS = 2;

	/**
	 * The feature id for the '<em><b>Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDVIDUAL_FAIRNESS__THRESHOLD = 3;

	/**
	 * The feature id for the '<em><b>Metric</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDVIDUAL_FAIRNESS__METRIC = 4;

	/**
	 * The number of structural features of the '<em>indvidual Fairness</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDVIDUAL_FAIRNESS_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>indvidual Fairness</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDVIDUAL_FAIRNESS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link UMLFairness.impl.criticalImpl <em>critical</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see UMLFairness.impl.criticalImpl
	 * @see UMLFairness.impl.UMLFairnessPackageImpl#getcritical()
	 * @generated
	 */
	int CRITICAL = 1;

	/**
	 * The feature id for the '<em><b>Protected Data</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITICAL__PROTECTED_DATA = 0;

	/**
	 * The feature id for the '<em><b>Base Structured Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITICAL__BASE_STRUCTURED_CLASSIFIER = 1;

	/**
	 * The number of structural features of the '<em>critical</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITICAL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>critical</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITICAL_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link UMLFairness.indvidualFairness <em>indvidual Fairness</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>indvidual Fairness</em>'.
	 * @see UMLFairness.indvidualFairness
	 * @generated
	 */
	EClass getindvidualFairness();

	/**
	 * Returns the meta object for the reference '{@link UMLFairness.indvidualFairness#getBase_BehavioredClassifier <em>Base Behaviored Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Behaviored Classifier</em>'.
	 * @see UMLFairness.indvidualFairness#getBase_BehavioredClassifier()
	 * @see #getindvidualFairness()
	 * @generated
	 */
	EReference getindvidualFairness_Base_BehavioredClassifier();

	/**
	 * Returns the meta object for the attribute list '{@link UMLFairness.indvidualFairness#getExplanatoryData <em>Explanatory Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Explanatory Data</em>'.
	 * @see UMLFairness.indvidualFairness#getExplanatoryData()
	 * @see #getindvidualFairness()
	 * @generated
	 */
	EAttribute getindvidualFairness_ExplanatoryData();

	/**
	 * Returns the meta object for the attribute list '{@link UMLFairness.indvidualFairness#getSensitiveDecisions <em>Sensitive Decisions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Sensitive Decisions</em>'.
	 * @see UMLFairness.indvidualFairness#getSensitiveDecisions()
	 * @see #getindvidualFairness()
	 * @generated
	 */
	EAttribute getindvidualFairness_SensitiveDecisions();

	/**
	 * Returns the meta object for the attribute '{@link UMLFairness.indvidualFairness#getThreshold <em>Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Threshold</em>'.
	 * @see UMLFairness.indvidualFairness#getThreshold()
	 * @see #getindvidualFairness()
	 * @generated
	 */
	EAttribute getindvidualFairness_Threshold();

	/**
	 * Returns the meta object for the attribute '{@link UMLFairness.indvidualFairness#getMetric <em>Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metric</em>'.
	 * @see UMLFairness.indvidualFairness#getMetric()
	 * @see #getindvidualFairness()
	 * @generated
	 */
	EAttribute getindvidualFairness_Metric();

	/**
	 * Returns the meta object for class '{@link UMLFairness.critical <em>critical</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>critical</em>'.
	 * @see UMLFairness.critical
	 * @generated
	 */
	EClass getcritical();

	/**
	 * Returns the meta object for the attribute list '{@link UMLFairness.critical#getProtectedData <em>Protected Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Protected Data</em>'.
	 * @see UMLFairness.critical#getProtectedData()
	 * @see #getcritical()
	 * @generated
	 */
	EAttribute getcritical_ProtectedData();

	/**
	 * Returns the meta object for the reference '{@link UMLFairness.critical#getBase_StructuredClassifier <em>Base Structured Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Structured Classifier</em>'.
	 * @see UMLFairness.critical#getBase_StructuredClassifier()
	 * @see #getcritical()
	 * @generated
	 */
	EReference getcritical_Base_StructuredClassifier();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UMLFairnessFactory getUMLFairnessFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link UMLFairness.impl.indvidualFairnessImpl <em>indvidual Fairness</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see UMLFairness.impl.indvidualFairnessImpl
		 * @see UMLFairness.impl.UMLFairnessPackageImpl#getindvidualFairness()
		 * @generated
		 */
		EClass INDVIDUAL_FAIRNESS = eINSTANCE.getindvidualFairness();

		/**
		 * The meta object literal for the '<em><b>Base Behaviored Classifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER = eINSTANCE.getindvidualFairness_Base_BehavioredClassifier();

		/**
		 * The meta object literal for the '<em><b>Explanatory Data</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDVIDUAL_FAIRNESS__EXPLANATORY_DATA = eINSTANCE.getindvidualFairness_ExplanatoryData();

		/**
		 * The meta object literal for the '<em><b>Sensitive Decisions</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDVIDUAL_FAIRNESS__SENSITIVE_DECISIONS = eINSTANCE.getindvidualFairness_SensitiveDecisions();

		/**
		 * The meta object literal for the '<em><b>Threshold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDVIDUAL_FAIRNESS__THRESHOLD = eINSTANCE.getindvidualFairness_Threshold();

		/**
		 * The meta object literal for the '<em><b>Metric</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDVIDUAL_FAIRNESS__METRIC = eINSTANCE.getindvidualFairness_Metric();

		/**
		 * The meta object literal for the '{@link UMLFairness.impl.criticalImpl <em>critical</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see UMLFairness.impl.criticalImpl
		 * @see UMLFairness.impl.UMLFairnessPackageImpl#getcritical()
		 * @generated
		 */
		EClass CRITICAL = eINSTANCE.getcritical();

		/**
		 * The meta object literal for the '<em><b>Protected Data</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRITICAL__PROTECTED_DATA = eINSTANCE.getcritical_ProtectedData();

		/**
		 * The meta object literal for the '<em><b>Base Structured Classifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CRITICAL__BASE_STRUCTURED_CLASSIFIER = eINSTANCE.getcritical_Base_StructuredClassifier();

	}

} //UMLFairnessPackage
