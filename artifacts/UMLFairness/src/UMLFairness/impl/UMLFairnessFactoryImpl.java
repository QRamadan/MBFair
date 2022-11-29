/**
 */
package UMLFairness.impl;

import UMLFairness.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UMLFairnessFactoryImpl extends EFactoryImpl implements UMLFairnessFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UMLFairnessFactory init() {
		try {
			UMLFairnessFactory theUMLFairnessFactory = (UMLFairnessFactory)EPackage.Registry.INSTANCE.getEFactory(UMLFairnessPackage.eNS_URI);
			if (theUMLFairnessFactory != null) {
				return theUMLFairnessFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UMLFairnessFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UMLFairnessFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS: return createindvidualFairness();
			case UMLFairnessPackage.CRITICAL: return createcritical();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public indvidualFairness createindvidualFairness() {
		indvidualFairnessImpl indvidualFairness = new indvidualFairnessImpl();
		return indvidualFairness;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public critical createcritical() {
		criticalImpl critical = new criticalImpl();
		return critical;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UMLFairnessPackage getUMLFairnessPackage() {
		return (UMLFairnessPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UMLFairnessPackage getPackage() {
		return UMLFairnessPackage.eINSTANCE;
	}

} //UMLFairnessFactoryImpl
