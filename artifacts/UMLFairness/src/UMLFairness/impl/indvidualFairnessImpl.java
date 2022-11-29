/**
 */
package UMLFairness.impl;

import UMLFairness.UMLFairnessPackage;
import UMLFairness.indvidualFairness;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.uml2.uml.BehavioredClassifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>indvidual Fairness</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link UMLFairness.impl.indvidualFairnessImpl#getBase_BehavioredClassifier <em>Base Behaviored Classifier</em>}</li>
 *   <li>{@link UMLFairness.impl.indvidualFairnessImpl#getExplanatoryData <em>Explanatory Data</em>}</li>
 *   <li>{@link UMLFairness.impl.indvidualFairnessImpl#getSensitiveDecisions <em>Sensitive Decisions</em>}</li>
 *   <li>{@link UMLFairness.impl.indvidualFairnessImpl#getThreshold <em>Threshold</em>}</li>
 *   <li>{@link UMLFairness.impl.indvidualFairnessImpl#getMetric <em>Metric</em>}</li>
 * </ul>
 *
 * @generated
 */
public class indvidualFairnessImpl extends MinimalEObjectImpl.Container implements indvidualFairness {
	/**
	 * The cached value of the '{@link #getBase_BehavioredClassifier() <em>Base Behaviored Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_BehavioredClassifier()
	 * @generated
	 * @ordered
	 */
	protected BehavioredClassifier base_BehavioredClassifier;

	/**
	 * The cached value of the '{@link #getExplanatoryData() <em>Explanatory Data</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExplanatoryData()
	 * @generated
	 * @ordered
	 */
	protected EList<String> explanatoryData;

	/**
	 * The cached value of the '{@link #getSensitiveDecisions() <em>Sensitive Decisions</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSensitiveDecisions()
	 * @generated
	 * @ordered
	 */
	protected EList<String> sensitiveDecisions;

	/**
	 * The default value of the '{@link #getThreshold() <em>Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final double THRESHOLD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getThreshold() <em>Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreshold()
	 * @generated
	 * @ordered
	 */
	protected double threshold = THRESHOLD_EDEFAULT;

	/**
	 * The default value of the '{@link #getMetric() <em>Metric</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetric()
	 * @generated
	 * @ordered
	 */
	protected static final String METRIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMetric() <em>Metric</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetric()
	 * @generated
	 * @ordered
	 */
	protected String metric = METRIC_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected indvidualFairnessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UMLFairnessPackage.Literals.INDVIDUAL_FAIRNESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioredClassifier getBase_BehavioredClassifier() {
		if (base_BehavioredClassifier != null && base_BehavioredClassifier.eIsProxy()) {
			InternalEObject oldBase_BehavioredClassifier = (InternalEObject)base_BehavioredClassifier;
			base_BehavioredClassifier = (BehavioredClassifier)eResolveProxy(oldBase_BehavioredClassifier);
			if (base_BehavioredClassifier != oldBase_BehavioredClassifier) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UMLFairnessPackage.INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER, oldBase_BehavioredClassifier, base_BehavioredClassifier));
			}
		}
		return base_BehavioredClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioredClassifier basicGetBase_BehavioredClassifier() {
		return base_BehavioredClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_BehavioredClassifier(BehavioredClassifier newBase_BehavioredClassifier) {
		BehavioredClassifier oldBase_BehavioredClassifier = base_BehavioredClassifier;
		base_BehavioredClassifier = newBase_BehavioredClassifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UMLFairnessPackage.INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER, oldBase_BehavioredClassifier, base_BehavioredClassifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getExplanatoryData() {
		if (explanatoryData == null) {
			explanatoryData = new EDataTypeUniqueEList<String>(String.class, this, UMLFairnessPackage.INDVIDUAL_FAIRNESS__EXPLANATORY_DATA);
		}
		return explanatoryData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSensitiveDecisions() {
		if (sensitiveDecisions == null) {
			sensitiveDecisions = new EDataTypeUniqueEList<String>(String.class, this, UMLFairnessPackage.INDVIDUAL_FAIRNESS__SENSITIVE_DECISIONS);
		}
		return sensitiveDecisions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getThreshold() {
		return threshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThreshold(double newThreshold) {
		double oldThreshold = threshold;
		threshold = newThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UMLFairnessPackage.INDVIDUAL_FAIRNESS__THRESHOLD, oldThreshold, threshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMetric() {
		return metric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetric(String newMetric) {
		String oldMetric = metric;
		metric = newMetric;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UMLFairnessPackage.INDVIDUAL_FAIRNESS__METRIC, oldMetric, metric));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER:
				if (resolve) return getBase_BehavioredClassifier();
				return basicGetBase_BehavioredClassifier();
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__EXPLANATORY_DATA:
				return getExplanatoryData();
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__SENSITIVE_DECISIONS:
				return getSensitiveDecisions();
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__THRESHOLD:
				return getThreshold();
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__METRIC:
				return getMetric();
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
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER:
				setBase_BehavioredClassifier((BehavioredClassifier)newValue);
				return;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__EXPLANATORY_DATA:
				getExplanatoryData().clear();
				getExplanatoryData().addAll((Collection<? extends String>)newValue);
				return;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__SENSITIVE_DECISIONS:
				getSensitiveDecisions().clear();
				getSensitiveDecisions().addAll((Collection<? extends String>)newValue);
				return;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__THRESHOLD:
				setThreshold((Double)newValue);
				return;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__METRIC:
				setMetric((String)newValue);
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
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER:
				setBase_BehavioredClassifier((BehavioredClassifier)null);
				return;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__EXPLANATORY_DATA:
				getExplanatoryData().clear();
				return;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__SENSITIVE_DECISIONS:
				getSensitiveDecisions().clear();
				return;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__THRESHOLD:
				setThreshold(THRESHOLD_EDEFAULT);
				return;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__METRIC:
				setMetric(METRIC_EDEFAULT);
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
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__BASE_BEHAVIORED_CLASSIFIER:
				return base_BehavioredClassifier != null;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__EXPLANATORY_DATA:
				return explanatoryData != null && !explanatoryData.isEmpty();
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__SENSITIVE_DECISIONS:
				return sensitiveDecisions != null && !sensitiveDecisions.isEmpty();
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__THRESHOLD:
				return threshold != THRESHOLD_EDEFAULT;
			case UMLFairnessPackage.INDVIDUAL_FAIRNESS__METRIC:
				return METRIC_EDEFAULT == null ? metric != null : !METRIC_EDEFAULT.equals(metric);
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
		result.append(" (explanatoryData: ");
		result.append(explanatoryData);
		result.append(", sensitiveDecisions: ");
		result.append(sensitiveDecisions);
		result.append(", threshold: ");
		result.append(threshold);
		result.append(", metric: ");
		result.append(metric);
		result.append(')');
		return result.toString();
	}

} //indvidualFairnessImpl
