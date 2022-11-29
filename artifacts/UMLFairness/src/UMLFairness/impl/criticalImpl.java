/**
 */
package UMLFairness.impl;

import UMLFairness.UMLFairnessPackage;
import UMLFairness.critical;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.uml2.uml.StructuredClassifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>critical</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link UMLFairness.impl.criticalImpl#getProtectedData <em>Protected Data</em>}</li>
 *   <li>{@link UMLFairness.impl.criticalImpl#getBase_StructuredClassifier <em>Base Structured Classifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class criticalImpl extends MinimalEObjectImpl.Container implements critical {
	/**
	 * The cached value of the '{@link #getProtectedData() <em>Protected Data</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtectedData()
	 * @generated
	 * @ordered
	 */
	protected EList<String> protectedData;

	/**
	 * The cached value of the '{@link #getBase_StructuredClassifier() <em>Base Structured Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_StructuredClassifier()
	 * @generated
	 * @ordered
	 */
	protected StructuredClassifier base_StructuredClassifier;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected criticalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UMLFairnessPackage.Literals.CRITICAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getProtectedData() {
		if (protectedData == null) {
			protectedData = new EDataTypeUniqueEList<String>(String.class, this, UMLFairnessPackage.CRITICAL__PROTECTED_DATA);
		}
		return protectedData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredClassifier getBase_StructuredClassifier() {
		if (base_StructuredClassifier != null && base_StructuredClassifier.eIsProxy()) {
			InternalEObject oldBase_StructuredClassifier = (InternalEObject)base_StructuredClassifier;
			base_StructuredClassifier = (StructuredClassifier)eResolveProxy(oldBase_StructuredClassifier);
			if (base_StructuredClassifier != oldBase_StructuredClassifier) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UMLFairnessPackage.CRITICAL__BASE_STRUCTURED_CLASSIFIER, oldBase_StructuredClassifier, base_StructuredClassifier));
			}
		}
		return base_StructuredClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredClassifier basicGetBase_StructuredClassifier() {
		return base_StructuredClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_StructuredClassifier(StructuredClassifier newBase_StructuredClassifier) {
		StructuredClassifier oldBase_StructuredClassifier = base_StructuredClassifier;
		base_StructuredClassifier = newBase_StructuredClassifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UMLFairnessPackage.CRITICAL__BASE_STRUCTURED_CLASSIFIER, oldBase_StructuredClassifier, base_StructuredClassifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UMLFairnessPackage.CRITICAL__PROTECTED_DATA:
				return getProtectedData();
			case UMLFairnessPackage.CRITICAL__BASE_STRUCTURED_CLASSIFIER:
				if (resolve) return getBase_StructuredClassifier();
				return basicGetBase_StructuredClassifier();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UMLFairnessPackage.CRITICAL__PROTECTED_DATA:
				getProtectedData().clear();
				getProtectedData().addAll((Collection<? extends String>)newValue);
				return;
			case UMLFairnessPackage.CRITICAL__BASE_STRUCTURED_CLASSIFIER:
				setBase_StructuredClassifier((StructuredClassifier)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UMLFairnessPackage.CRITICAL__PROTECTED_DATA:
				getProtectedData().clear();
				return;
			case UMLFairnessPackage.CRITICAL__BASE_STRUCTURED_CLASSIFIER:
				setBase_StructuredClassifier((StructuredClassifier)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UMLFairnessPackage.CRITICAL__PROTECTED_DATA:
				return protectedData != null && !protectedData.isEmpty();
			case UMLFairnessPackage.CRITICAL__BASE_STRUCTURED_CLASSIFIER:
				return base_StructuredClassifier != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (protectedData: ");
		result.append(protectedData);
		result.append(')');
		return result.toString();
	}

} //criticalImpl
