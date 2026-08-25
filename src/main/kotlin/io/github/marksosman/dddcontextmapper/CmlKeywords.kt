package io.github.marksosman.dddcontextmapper

object CmlKeywords {

    val DECLARATIONS = listOf(
        "ContextMap", "BoundedContext", "Domain", "Subdomain",
        "Aggregate", "Module", "Entity", "ValueObject", "Service",
        "Repository", "DomainEvent", "CommandEvent", "UseCase", "UserStory",
        "Enum", "Application"
    )

    val HEADER_CLAUSES = listOf("implements", "refines", "realizes", "supports")

    val PROPERTIES = listOf(
        "contains", "type", "state", "domainVisionStatement",
        "implementationTechnology", "responsibilities", "knowledgeLevel",
        "businessModel", "evolution", "downstreamRights", "exposedAggregates",
        "aggregateRoot"
    )

    val RELATIONSHIPS = listOf(
        "Partnership", "SharedKernel", "CustomerSupplier", "UpstreamDownstream",
        "OpenHostService", "PublishedLanguage", "AntiCorruptionLayer", "Conformist"
    )

    val DECLARATION_SET = DECLARATIONS.toSet()
    val HEADER_CLAUSE_SET = HEADER_CLAUSES.toSet()
    val PROPERTY_SET = PROPERTIES.toSet()
    val RELATIONSHIP_SET = RELATIONSHIPS.toSet()
}
