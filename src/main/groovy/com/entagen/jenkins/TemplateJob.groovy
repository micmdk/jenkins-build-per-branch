package com.entagen.jenkins

class TemplateJob {
    String jobName
    String baseJobName
    String templateBranchName

    String jobNameForBranch(String branchName) {
        // git branches often have a forward slash in them, but they make jenkins cranky, turn it into an underscore
        String safeBranchName = branchName.replaceAll('/', '_')
        // Replace template name DBA-Master-Unit-T to DBA-feature-MOBDK-xxxx-T
        String replacedBranchName = this.jobName.replaceAll(~/(?i)Master/, safeBranchName)
        // Remove -T form branch name
        return replacedBranchName[0..replacedBranchName.length()-3]
    }
    
    ConcreteJob concreteJobForBranch(String branchName) {
        ConcreteJob concreteJob = new ConcreteJob(templateJob: this, branchName: branchName, jobName: jobNameForBranch(branchName) )
    }
}
