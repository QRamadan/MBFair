/**
 */
package UMLFairness.impl;

import UMLFairness.UMLFairnessFactory;
import UMLFairness.UMLFairnessPackage;
import UMLFairness.critical;
import UMLFairness.indvidualFairness;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UMLFairnessPackageImpl extends EPackageImpl implements UMLFairnessPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indvidualFairnessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass criticalEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see UMLFairness.UMLFairnessPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UMLFairnessPackageImpl() {
		super(eNS_URI, UMLFairnessFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link UMLFairnessPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UMLFairnessPackage init() {
		if (isInited) return (UMLFairnessPackage)EPackage.Registry.INSTANCE.getEPackage(UMLFairnessPackage.eNS_URI);

		// Obtain or create and register package
		UMLFairnessPackageImpl theUMLFairnessPackage = (UMLFairnessPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UMLFairnessPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UMLFairnessPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theUMLFairnessPackage.createPackageContents();

		// Initialize created meta-data
		theUMLFairnessPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUMLFairnessPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UMLFairnessPackage.eNS_URI, theUMLFairnessPackage);
		return theUMLFairnessPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getindvidualFairness() {
		return indvidualFairnessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getindvidualFairness_Base_BehavioredClassifier() {
		return (EReference)indvidualFairnessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getindvidualFairness_ExplanatoryData() {
		return (EAttribute)indvidualFairnessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getindvidualFairness_SensitiveDecisions() {
		return (EAttribute)indvidualFairnessEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getindvidualFairness_Threshold() {
		return (EAttribute)indvidualFairnessEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getindvidualFairness_Metric() {
		return (EAttribute)indvidualFairnessEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getcritical() {
		return criticalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getcritical_ProtectedData() {
		return (EAttribute)criticalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getcritical_Base_StructuredClassifier() {
		return (EReference)criticalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UMLFairnessFactory getUMLFairnessFactory() {
		return (UMLFairnessFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		indvidualFairnessEClass = createEClass(INDVIDUAL_FAIRNESS);
		createEReference(indvidualFairnessEClass, INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER);
		createEAttribute(indvidualFairnessEClass, INDVIDUAL_FAIRNESS__EXPLANATORY_DATA);
		createEAttribute(indvidualFairnessEClass, INDVIDUAL_FAIRNESS__SENSITIVE_DECISIONS);
		createEAttribute(indvidualFairnessEClass, INDVIDUAL_FAIRNESS__THRESHOLD);
		createEAttribute(indvidualFairnessEClass, INDVIDUAL_FAIRNESS__METRIC);

		criticalEClass = createEClass(CRITICAL);
		createEAttribute(criticalEClass, CRITICAL__PROTECTED_DATA);
		createEReference(criticalEClass, CRITICAL__BASE_STRUCTURED_CLASSIFIER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(indvidualFairnessEClass, indvidualFairness.class, "indvidualFairness", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getindvidualFairness_Base_BehavioredClassifier(), theUMLPackage.getBehavioredClassifier(), null, "base_BehavioredClassifier", null, 1, 1, indvidualFairness.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getindvidualFairness_ExplanatoryData(), ecorePackage.getEString(), "explanatoryData", null, 0, -1, indvidualFairness.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getindvidualFairness_SensitiveDecisions(), ecorePackage.getEString(), "sensitiveDecisions", null, 0, -1, indvidualFairness.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getindvidualFairness_Threshold(), ecorePackage.getEDouble(), "threshold", null, 1, 1, indvidualFairness.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getindvidualFairness_Metric(), ecorePackage.getEString(), "metric", null, 1, 1, indvidualFairness.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(criticalEClass, critical.class, "critical", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getcritical_ProtectedData(), ecorePackage.getEString(), "protectedData", null, 0, -1, critical.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getcritical_Base_StructuredClassifier(), theUMLPackage.getStructuredClassifier(), null, "base_StructuredClassifier", null, 1, 1, critical.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/uml2/2.0.0/UML
		createUMLAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/uml2/2.0.0/UML</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUMLAnnotations() {
		String source = "http://www.eclipse.org/uml2/2.0.0/UML";	
		addAnnotation
		  (indvidualFairnessEClass, 
		   source, 
		   new String[] {
			 "originalName", "indvidual-Fairness"
		   });
	}

} //UMLFairnessPackageImpl
