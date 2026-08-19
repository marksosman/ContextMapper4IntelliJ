package io.github.marksosman.dddcontextmapper

object CmlKeywords {

    val DECLARATIONS = listOf(
        "ContextMap", "BoundedContext", "Domain", "Subdomain",
        "Aggregate", "Module", "Entity", "ValueObject", "Service",
        "Repository", "DomainEvent", "CommandEvent", "UseCase", "UserStory",
        "Enum", "Application"
    )

    val PROPERTIES = listOf(
        "contains", "type", "state", "domainVisionStatement",
        "implementationTechnology", "responsibilities", "knowledgeLevel",
        "implements", "realizes", "refines", "exposedAggregates",
        "aggregateRoot", "upstream", "downstream",
        "key", "def", "abstract", "extends", "not"
    )

    val RELATIONSHIPS = listOf(
        "Partnership", "SharedKernel", "CustomerSupplier", "UpstreamDownstream",
        "OpenHostService", "PublishedLanguage", "AntiCorruptionLayer", "Conformist"
    )

    val ALL: Set<String> = (DECLARATIONS + PROPERTIES + RELATIONSHIPS).toSet()
}