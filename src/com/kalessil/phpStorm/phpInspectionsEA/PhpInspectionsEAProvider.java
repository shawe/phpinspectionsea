package com.kalessil.phpStorm.phpInspectionsEA;

import com.intellij.codeInspection.InspectionToolProvider;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.apiUsage.*;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.codeSmell.AmbiguousMethodsCallsInArrayMappingInspector;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.codeSmell.MoreThanThreeArgumentsInspector;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.earlyReturns.NestedPositiveIfStatementsInspector;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.languageConstructions.*;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.propel16.CountOnPropelCollectionInspector;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.semanticalAnalysis.*;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.semanticalTransformations.*;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.strictInterfaces.ArrayTypeOfParameterByDefaultValueInspector;

/*
===TODO===:

NotOptimalIfConditions
        - '... instanceof ...'|'is_*(...)' vs '=== null'

IsNullFunctionUsageInspector
        - !is_null -> show different message

AdditionOperationOnArraysInspection:
        - re-implement to check any of binary/mathematical operations has been applied on an array

SlowArrayOperationsInLoopInspector:
        - more functions with O(n) complexity, e.g. array_unique

DefaultValueInElseBranchInspector
        - new pattern:
                if (...) {
                    ...
                    if (...) {
                        $var = <expression 1>;
                    } else {
                        $var = <expression 2>;
                    }
                } else {
                    $var = <expression 2>;
                }

===POOL===

API usage: do not use aliases
        is_double	    is_float
        is_integer	    is_int
        is_long	        is_int
        is_real	        is_float
        sizeof	        count

Confusing construct: BO ? bool|BO : BO|bool

Confusing construct: Unnecessary 'return/continue' statement

Confusing construct: IfReturnReturnSimplificationInspector:
        ifReturnElseReturn
        ifReturn[,ElseIfReturn]{1,}ElseReturn - possibly some branches can be merged

PHP 5 migration: reflection API usage (ReflectionClass):
        constant, is_a, method_exists, property_exists, is_subclass_of are from PHP 4 world
        and not dealing with traits, annotations and so on. Mark deprecated.

*/
public class PhpInspectionsEAProvider implements InspectionToolProvider {
    @Override
    public Class[] getInspectionClasses() {
        return new Class[]{
                IsNullFunctionUsageInspector.class,
                IsEmptyFunctionUsageInspector.class,
                UnSafeIsSetOverArrayInspector.class,
                ForgottenDebugOutputInspector.class,

                UnNecessaryDoubleQuotesInspector.class,
                TypeUnsafeComparisonInspector.class,
                TypeUnsafeArraySearchInspector.class,

                IfConditionalsWithoutGroupStatementInspector.class,

                NestedPositiveIfStatementsInspector.class,
                TernaryOperatorSimplifyInspector.class,
                IfReturnReturnSimplificationInspector.class,
                /*IfExpressionInEarlyReturnContextInspector.class,*/
                DefaultValueInElseBranchInspector.class,

                /*DefaultValuesForCallableParametersInspector.class,*/
                ArrayTypeOfParameterByDefaultValueInspector.class,
                SenselessCommaInArrayDefinitionInspector.class,

                MoreThanThreeArgumentsInspector.class,
                DirnameCallOnFileConstantInspector.class,
                AmbiguousMethodsCallsInArrayMappingInspector.class,
                SequentialUnSetCallsInspector.class,

                NotOptimalIfConditionsInspection.class,
                StrlenInEmptyStringCheckContextInspection.class,
                OnlyWritesOnParameterInspector.class,
                AmbiguousMemberInitializationInspector.class,
                LoopWhichDoesNotLoopInspector.class,

                TypesCastingWithFunctionsInspector.class,
                ElvisOperatorCanBeUsedInspector.class,
                SenselessTernaryOperatorInspector.class,
                AdditionOperationOnArraysInspection.class,
                ArrayCastingEquivalentInspector.class,

                CountOnPropelCollectionInspector.class,
                ForeachSourceInspector.class,
                CallableParameterUseCaseInTypeContextInspection.class,

                MissingParentConstructorCallInspector.class,
                ClassMethodNameMatchesFieldNameInspector.class,
                ClassReImplementsParentInterfaceInspector.class,
                ClassOverridesFieldOfSuperClassInspector.class,
                SingletonFactoryPatternViolationInspector.class,
                PrivateConstructorSemanticsInspector.class,

                CallableInLoopTerminationConditionInspector.class,
                SlowArrayOperationsInLoopInspector.class,
                NestedTernaryOperatorInspector.class,
                UselessUnsetInspector.class
        };
    }
}
